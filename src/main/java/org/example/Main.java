package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;
import org.httpobjects.jetty.HttpObjectsJettyHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

   public static void main(String[] args) throws Exception {
       Map<Integer, TodoDto> inMemoryMap = new HashMap<Integer, TodoDto>();

       final String RESOURCES_PATH = "/";

       HttpObject rootResource = new HttpObject("/"){
           @Override
           public Response get(Request req) {
               InputStream stream = null;
               try {
                   stream = getClass().getResourceAsStream("/index.html");
               } catch (Exception e){
                   System.out.println("Can't find index.html");
               }
               return OK(Bytes("text/html", stream));
           }
       };
       TodosResource todosResource = new TodosResource("/todo", inMemoryMap);
       TodoResource todoResource = new TodoResource("/todo/{id}", inMemoryMap);
       StaticResource staticResource = new StaticResource("/{path*}", RESOURCES_PATH);
       HttpObjectsJettyHandler.launchServer(8080, rootResource, todosResource, todoResource, staticResource);
   }

}
