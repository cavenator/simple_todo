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
import java.util.Map;
import java.util.HashMap;

public class HelloWorld {

   public static void main(String[] args) throws Exception {
      Server server = new Server(8080);
      final Map<Integer, TodoDto> inMemoryMap = new HashMap<Integer, TodoDto>();
      TodosHttpServlet todosServlet = new TodosHttpServlet("/todo", inMemoryMap);
      TodoHttpServlet todoServlet = new TodoHttpServlet("/todo/{id}", inMemoryMap);
      SimpleHttpObject obj = new SimpleHttpObject(todosServlet, todoServlet);
      server.setHandler(obj);
      server.start();
      server.join();
   }

   public static class SimpleHttpObject extends AbstractHandler{

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
         System.out.println("I am in handle; target is "+target);

         boolean servletFound = servletInvoker.invokeServletWithPath(target, request, response);
         if (servletFound){
            baseRequest.setHandled(true);
            return;
         }
         

         if (baseRequest.isHandled()){
             System.out.println("base request is handled!  Leavin .....");
             return;
         } else {
             System.out.println("base is not handled! Let's do this!");
             defaultResourceHandler.handle(target, baseRequest, request, response);
             if (!baseRequest.isHandled()){
                 response.setContentType("text/html");
                 response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                 response.getWriter().println("Unable to find path: "+target);
                 baseRequest.setHandled(true);
             }
         }
         
      }

      private void invoke(HttpServletRequest request, HttpServletResponse response, AbstractHttpServlet servlet) throws IOException, ServletException {
         System.out.println("Inside invoke method");
         String method = request.getMethod();
         if (method.equals("GET")){
             servlet.get(request, response);
         } else if (method.equals("PUT")){
             servlet.put(request, response);
         } else if (method.equals("POST")){
             servlet.post(request, response);
         } else if (method.equals("DELETE")){
             servlet.delete(request, response);
         } else {
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         }
      }

   }

   public static ResourceHandler getBasicResourceHandler(){
	ResourceHandler resourceHandler = new ResourceHandler();
	resourceHandler.setResourceBase("./src/main/resources/");
	resourceHandler.setDirectoriesListed(true);
	resourceHandler.setWelcomeFiles(new String[]{ "index.html","pretty_page.html" });
	return resourceHandler;
   }

}
