package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

import java.util.HashMap;
import java.util.Map;

public class TodoResource extends HttpObject {
    private Map<String, String> inMemoryMap;

    public TodoResource(String pathPattern) {
        super(pathPattern);
        this.inMemoryMap = new HashMap<String, String>();
    }

    public TodoResource(String pathPattern, Map<String, String> map){
        super(pathPattern);
        this.inMemoryMap = map;
    }

    @Override
    public Response get(Request req) {
        return super.get(req);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Response post(Request req) {
        return super.post(req);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Response delete(Request req) {
        return super.delete(req);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
