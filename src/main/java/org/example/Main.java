package org.example;

import org.eclipse.jetty.server.Server;
import java.util.Map;
import java.util.HashMap;

public class Main {

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

}
