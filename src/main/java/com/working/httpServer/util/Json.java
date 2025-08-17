package com.working.httpServer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.working.httpServer.config.Configration;

import java.io.IOException;

public class Json {
    //used for serializaiton and deserialization, converting JSON to java object
    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    private static  ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        // config that makes the parsing not fail in case of a missing property
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    // to convert a raw JSON string into a navigable tree structure (JsonNode) using Jackson, allowing you to programmatically read values from JSON.
    public static JsonNode parse(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }

    //to convert the JsonNode into a Java object of type clazz.
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException{
        //to convert the JsonNode into a Java object of type clazz.
        return myObjectMapper.treeToValue(node,clazz);
    }
 
//converts a Java object into a JsonNode — a tree-like structure representing JSON
    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }

    
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }


    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    //method to get the jsonNode into the string format
    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }
}
