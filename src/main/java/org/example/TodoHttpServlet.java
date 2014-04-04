package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

//path = /todo/{id}
public class TodoHttpServlet extends AbstractHttpServlet {

      private Map<Integer, TodoDto> inMemoryMap = new HashMap<Integer, TodoDto>();

      public TodoHttpServlet(String path){
         super(path);
      }

      public TodoHttpServlet(String path, Map<Integer, TodoDto> map){
          this(path);
          this.inMemoryMap = map;
      }

      protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          ErrorDto errorDto = new ErrorDto();
          String id = pathVars.get("id");
          Integer numId;
          try {
             numId = Integer.parseInt(id);
          } catch (NumberFormatException nfe){
             errorDto.reason = "Id passed in is not an integer! Here is what you passed in: "+id;
             draftResponse(response, errorDto, response.SC_BAD_REQUEST);
             return;
          }
          TodoDto dto = inMemoryMap.get(numId);
          if (dto == null){
             errorDto.reason = "Dto does not exist";
             draftResponse(response, errorDto, response.SC_NOT_FOUND);
             return;
          }

          draftResponse(response, dto, response.SC_OK);
      }

      protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          ErrorDto errorDto = new ErrorDto();
          String id = request.getParameter("id");
          Integer numId;
          try {
             numId = Integer.parseInt(id);
          } catch (NumberFormatException nfe){
             errorDto.reason = "Id passed in is not an integer! Here is what you passed in: "+id;
             draftResponse(response, errorDto, response.SC_BAD_REQUEST);
             return;
          }
          TodoDto dto = inMemoryMap.remove(numId);
          if (dto == null){
             errorDto.reason = "You attempted to remove a non-existent key";
             draftResponse(response, errorDto, response.SC_NOT_FOUND);
             return;
          }

          draftResponse(response, response.SC_OK);
      }

      private void draftResponse(HttpServletResponse response, Object obj, int statusCode) throws IOException{
          draftResponse(response, statusCode);
          response.getWriter().write(JsonUtil.toJson(obj));
      }

      private void draftResponse(HttpServletResponse response, int statusCode){
	  response.setContentType("application/json;charset=utf-8");
	  response.setStatus(statusCode);
      }
}
