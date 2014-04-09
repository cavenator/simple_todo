package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import java.util.Map;
import java.util.HashMap;

public class SimpleHttpObject extends AbstractHandler{

	private AbstractHttpServlet[] servlets;
	private ResourceHandler defaultResourceHandler;
	private ServletInvoker servletInvoker;

	public SimpleHttpObject(){
		this.defaultResourceHandler = getBasicResourceHandler();
	}

	public SimpleHttpObject(AbstractHttpServlet... h){
		this();
		this.servlets = h;
		this.servletInvoker = new ServletInvoker(h);
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		boolean servletFound = servletInvoker.invokeServletWithPath(target, request, response);
		if (servletFound){
			baseRequest.setHandled(true);
			return;
		}


		if (baseRequest.isHandled()){
			return;
		} else {
			defaultResourceHandler.handle(target, baseRequest, request, response);
			if (!baseRequest.isHandled()){
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("Unable to find path: "+target);
				baseRequest.setHandled(true);
			}
		}

	}

	private ResourceHandler getBasicResourceHandler(){
	       ResourceHandler resourceHandler = new ResourceHandler();
	       resourceHandler.setResourceBase("./src/main/resources/");
	       resourceHandler.setDirectoriesListed(true);
	       resourceHandler.setWelcomeFiles(new String[]{ "index.html" });
	       return resourceHandler;
	}

}
