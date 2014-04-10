package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;
import org.httpobjects.util.MimeTypeTool;

import javax.activation.MimeType;
import java.io.*;

public class StaticResource extends HttpObject {

    String resourcesPath;

    public StaticResource(String pathPattern, String resourcesPath){
        super(pathPattern);
        this.resourcesPath = resourcesPath;
    }

    @Override
    public Response get(Request req) {
        String rawPath = req.path().valueFor("path");
        InputStream stream = null;

        try{
            stream = new FileInputStream(new File(resourcesPath+rawPath));
        } catch (FileNotFoundException fnfe) {
            System.out.println("can't find resource: "+rawPath);
        }

        if (stream == null)
           return NOT_FOUND();

        MimeTypeTool mimeTypeTool = new MimeTypeTool();
        return OK(Bytes(mimeTypeTool.guessMimeTypeFromName(rawPath), stream));
    }
}
