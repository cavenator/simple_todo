package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

import java.util.HashMap;
import java.util.Map;

public class TodoResource extends HttpObject {
    private Map<Integer, TodoDto> inMemoryMap;

    public TodoResource(String pathPattern, Map<Integer, TodoDto> map){
        super(pathPattern);
        this.inMemoryMap = map;
    }

    @Override
    public Response delete(Request req) {
        String id = req.path().valueFor("id");
        Integer numId;
        try {
            numId = Integer.parseInt(id);
        } catch (Exception e){
            return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("invalid id: "+id))));
        }

        TodoDto todo = inMemoryMap.remove(numId);
        if (todo != null)
            return NO_CONTENT();

        return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("todo that did not exist prior t DELETE"))));
    }

    @Override
    public Response get(Request req) {
        String id = req.path().valueFor("id");
        Integer numId;
        try {
            numId = Integer.parseInt(id);
        } catch (Exception e){
            return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("invalid id: "+id))));
        }

        TodoDto todo = inMemoryMap.get(numId);
        if (todo != null)
            return OK(Json(JsonUtil.toJson(todo)));

        return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("todo does not exist with id: "+id))));
    }
}
