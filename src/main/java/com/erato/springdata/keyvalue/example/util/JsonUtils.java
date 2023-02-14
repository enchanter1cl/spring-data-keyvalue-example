package com.erato.springdata.keyvalue.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ZhangYuan
 * @date 2023/2/14
 */
public class JsonUtils {
    
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    public static <T> T readValue(String str, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T readValue(String str, TypeReference type) {
        try {
            return (T) OBJECT_MAPPER.readValue(str, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String writeValueAsString(Object obj){
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
