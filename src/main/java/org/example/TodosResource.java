package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class TodosResource extends HttpObject {
    private Map<Integer, TodoDto> inMemoryMap;

    public TodosResource(String pathPattern, Map<Integer, TodoDto> map){
        super(pathPattern);
        this.inMemoryMap = map;
    }

    @Override
    public Response get(Request req) {
        return OK(Json(JsonUtil.toJson(inMemoryMap.values())));
    }

    @Override
    public Response post(Request req) {
        OutputStream outputStream = new ByteArrayOutputStream();
        req.representation().write(outputStream);
        String out = outputStream.toString();

        TodoDto dto = JsonUtil.fromJson(out, TodoDto.class);
        inMemoryMap.put(dto.id, dto);
        return OK(Json(JsonUtil.toJson(dto)));
    }

    @Override
    public Response delete(Request req) {
        this.inMemoryMap.clear();
        return OK(null);
    }
}
