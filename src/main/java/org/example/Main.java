package org.example;

import org.httpobjects.jetty.HttpObjectsJettyHandler;

import java.util.HashMap;
import java.util.Map;

public class Main {

   public static void main(String[] args) throws Exception {
       Map<String, String> inMemoryMap = new HashMap<String, String>();
       TodoResource todoResource = new TodoResource("/todo", inMemoryMap);
       TodosResource todosResource = new TodosResource("/todo/{id}", inMemoryMap);
       StaticResource staticResource = new StaticResource("/{path*}");
       HttpObjectsJettyHandler.launchServer(8080, todoResource, todosResource, staticResource);
   }

}
