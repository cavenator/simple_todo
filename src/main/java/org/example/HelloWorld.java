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
      TodosHttpServlet todosServlet = new TodosHttpServlet("/todo");
      SimpleHttpObject obj = new SimpleHttpObject(todosServlet);
      server.setHandler(obj);
      server.start();
      server.join();
   }

   public static class SimpleHttpObject extends AbstractHandler{

      private AbstractHttpServlet[] servlets;
      private ResourceHandler defaultResourceHandler;

      public SimpleHttpObject(){
          this.defaultResourceHandler = getBasicResourceHandler();
      }
      
      public SimpleHttpObject(AbstractHttpServlet... h){
         this();
         this.servlets = h;
      }

      @Override
      public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
         System.out.println("I am in handle; target is "+target);

         for (AbstractHttpServlet servlet: servlets){
            if (target.equals(servlet.getPath())){
               System.out.println("path is matched");
               invoke(request, response, servlet);
               baseRequest.setHandled(true);
            }
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
