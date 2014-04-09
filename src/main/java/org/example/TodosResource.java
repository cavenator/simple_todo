package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

import java.util.HashMap;
import java.util.Map;

public class TodosResource extends HttpObject {
    private Map<String, String> inMemoryMap;

    public TodosResource(String pathPattern) {
        super(pathPattern);
        this.inMemoryMap = new HashMap<String, String>();
    }

    public TodosResource(String pathPattern, Map<String, String> map){
        super(pathPattern);
        this.inMemoryMap = map;
    }

    @Override
    public Response delete(Request req) {
        return super.delete(req);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Response get(Request req) {
        return super.get(req);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Response put(Request req) {
        return super.put(req);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
