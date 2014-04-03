package org.example;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class HelloWorld {

   public static void main(String[] args) throws Exception {
      Server server = new Server(8080);
      ContextHandler context = new ContextHandler("/");
      context.setHandler(getBasicResourceHandler());

      ServletContextHandler servletContext = new ServletContextHandler();
      servletContext.setContextPath("/");
      servletContext.addServlet(new ServletHolder(new TodosHttpServlet()), "/todo");

      ContextHandlerCollection handlerCollection = new ContextHandlerCollection();
      handlerCollection.setHandlers(new Handler[]{context, servletContext});

      server.setHandler(handlerCollection);
      server.start();
      server.join();
   }

   public static ResourceHandler getBasicResourceHandler(){
	ResourceHandler resourceHandler = new ResourceHandler();
	resourceHandler.setResourceBase("./src/main/resources/");
	resourceHandler.setDirectoriesListed(true);
	resourceHandler.setWelcomeFiles(new String[]{ "index.html","pretty_page.html" });
	return resourceHandler;
   }

}
