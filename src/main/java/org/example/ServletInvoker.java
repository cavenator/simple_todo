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
                invoke(servlet, request, response);
                isFound = true;
                break;
          }
      }

      return isFound;
   }

   private void invoke(AbstractHttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       String method = request.getMethod();
       if (method.equals("GET")) servlet.get(request, response);
       else if (method.equals("POST")) servlet.post(request, response);
       else if (method.equals("PUT")) servlet.put(request, response);
       else if (method.equals("DELETE")) servlet.delete(request, response);
       else {
           response.setStatus(HttpServletResponse.SC_NOT_FOUND);
       }
   }
}
