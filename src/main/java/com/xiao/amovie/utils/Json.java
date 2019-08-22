package com.xiao.amovie.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author xiao
 */
@SuppressWarnings("ALL")
public class Json {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    }

    public static String toJson(Object o) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }


    public static <T> T parseObject(String s, Class<T> clazz) {
        try {
            return MAPPER.readValue(s, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }

    }


    public static <T> List<T> parseArray(String s, Class<T> clazz) {
        try {
            return MAPPER.readValue(s, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }


}
