package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;
import org.httpobjects.jetty.HttpObjectsJettyHandler;
import org.httpobjects.util.ClasspathResourceObject;
import org.httpobjects.util.ClasspathResourcesObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

   public static void main(String[] args) throws Exception {
       Map<Integer, TodoDto> inMemoryMap = new HashMap<Integer, TodoDto>();

       final String RESOURCES_PATH = "/";

       ClasspathResourceObject rootResource = new ClasspathResourceObject(RESOURCES_PATH,"/index.html", Main.class);
       TodosResource todosResource = new TodosResource("/todo", inMemoryMap);
       TodoResource todoResource = new TodoResource("/todo/{id}", inMemoryMap);
       ClasspathResourcesObject staticResources = new ClasspathResourcesObject("/{resource*}", Main.class, RESOURCES_PATH);

       HttpObjectsJettyHandler.launchServer(8080, rootResource, todosResource, todoResource, staticResources);
   }

}
