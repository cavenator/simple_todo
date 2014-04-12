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

public class TodosHttpServlet extends AbstractHttpServlet {

      private Map<Integer, TodoDto> inMemoryMap = new HashMap<Integer, TodoDto>();

      public TodosHttpServlet(String path){
         super(path);
      }

      public TodosHttpServlet(String path, Map<Integer, TodoDto> map){
          this(path);
          this.inMemoryMap = map;
      }

      protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          draftResponse(response, inMemoryMap.values(), HttpServletResponse.SC_OK);
      }

      protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String requestBody = request.getReader().readLine();
           TodoDto dto = JsonUtil.fromJson(requestBody, TodoDto.class);
           inMemoryMap.put(dto.id, dto);

	       draftResponse(response, dto, HttpServletResponse.SC_CREATED);
      }

      protected void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           inMemoryMap.clear();
	       response.setStatus(HttpServletResponse.SC_OK);
      }
}
