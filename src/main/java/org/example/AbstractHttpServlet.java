package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public abstract class AbstractHttpServlet extends HttpServlet {

   protected String path;
   private List<PathSegment> segments;
   protected Map<String, String> pathVars;
 
   public AbstractHttpServlet(String path){
      pathVars = new HashMap<String, String>();
      segments = new LinkedList<PathSegment>();
      parsePathIntoSegments(path);
   }
 
   private void parsePathIntoSegments(String path){
      String[] parts = path.split("/");
      for (String part: parts){
            if (part.startsWith("{"))
               segments.add(new PathSegment(part.substring(1, part.length() - 1), true));
            else
               segments.add(new PathSegment(part, false));
      }
   }

   public boolean matchPath(String path){
       String[] parts = path.split("/");
       if (parts.length != segments.size()) return false;
   
       boolean doesMatch = true;
   
       for (int i = 0; i < parts.length; i++){
           String part = parts[i];
           PathSegment seg = segments.get(i);
           if (seg.isPathVar())  continue;
           else if (!part.equals(seg.getSegment())) return false;
       }
   
       return doesMatch;
   }

   public void loadPathVariables(String path){
         pathVars.clear();
         String[] parts = path.split("/");
         for (int i =0; i < parts.length; i++){
            String part = parts[i];
            PathSegment segment = segments.get(i);
            if (segment.isPathVar()){
               System.out.println(part + " is a path variable");
               String pathVar = part;
               pathVars.put(segment.getSegment(), pathVar);
            }
         }
   }

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

   public class PathSegment{
      private String segment;
      private boolean isPathVar;

      public PathSegment(String segment, boolean isPathVar){
         this.segment = segment;
         this.isPathVar = isPathVar;
      }

      public boolean isPathVar(){
         return isPathVar;
      }

      public String getSegment(){
         return segment;
      }
 
      public String toString(){
         return "segment: "+segment + ", isPathVar: "+isPathVar;
      }
   }
}
