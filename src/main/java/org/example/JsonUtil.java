package org.example;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class JsonUtil {

   private static ObjectMapper mapper = new ObjectMapper();

   static {
        mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
   }

   public static String toJson(Object object) {
        Writer strWriter = new StringWriter();
        try {
            mapper.writeValue(strWriter, object);
        } catch (IOException e) {
            throw new RuntimeException("unable to generate json", e);
        }
        return strWriter.toString();
   }


   public static <T> T fromJson(String json, Class<T> theClass) {
        System.out.println(theClass);
        System.out.println(json);
        try {
            return mapper.readValue(json, theClass);
        } catch (IOException e) {
            throw new RuntimeException("unable to parse json", e);
        }
   }

   public static <T> List<T> listFromJson(String json, Class<T> elementType) {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, elementType);
            return mapper.readValue(json, collectionType);
        } catch (IOException e) {
            throw new RuntimeException("unable to parse json", e);
        }
   }
}
