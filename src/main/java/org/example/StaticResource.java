package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

public class StaticResource extends HttpObject {

    public StaticResource(String pathPattern) {
        super(pathPattern);
    }

    @Override
    public Response get(Request req) {
        return super.get(req);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
