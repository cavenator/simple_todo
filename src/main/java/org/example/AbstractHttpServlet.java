package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public abstract class AbstractHttpServlet extends HttpServlet {

   protected String path;

   public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      doGet(request, response);
   }

   public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      doPost(request, response);
   }

   public void put(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      doPut(request, response);
   }

   public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      doDelete(request, response);
   }

   public String getPath(){
      return path;
   }
}
