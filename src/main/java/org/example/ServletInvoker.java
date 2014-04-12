package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class ServletInvoker{
   private AbstractHttpServlet[] servlets;

   public ServletInvoker(AbstractHttpServlet[] servlets){
      this.servlets = servlets;
   }

   public boolean invokeServletWithPath(String path, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      boolean isFound = false;

      for (AbstractHttpServlet servlet: servlets){
          if (servlet.matchPath(path)){ 
                servlet.loadPathVariables(path);
                servlet.service(request, response);
                isFound = true;
                break;
          }
      }

      return isFound;
   }
}
