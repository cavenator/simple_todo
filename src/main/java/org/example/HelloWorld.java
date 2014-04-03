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
      SimpleHttpObject obj = new SimpleHttpObject();
      server.setHandler(obj);
      server.start();
      server.join();
   }

   public static class SimpleHttpObject extends AbstractHandler{

      private Handler[] handlers;
      private ResourceHandler defaultResourceHandler;

      public SimpleHttpObject(){
          this.defaultResourceHandler = getBasicResourceHandler();
      }
      
      public SimpleHttpObject(Handler... h){
         this();
         this.handlers = h;
      }

      @Override
      public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
         System.out.println("I am in handle; target is "+target);
         if (baseRequest.isHandled()){
             System.out.println("base request is handled!  Leavin .....");
             return;
         } else {
             System.out.println("base is not handled! Let's do this!");
             defaultResourceHandler.handle(target, baseRequest, request, response);
             System.out.println(baseRequest.isHandled());
             baseRequest.setHandled(true);
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
