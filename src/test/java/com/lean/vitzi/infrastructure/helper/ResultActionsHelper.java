package com.lean.vitzi.infrastructure.helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class ResultActionsHelper {

    /**
     * @param resultActions
     * @param clazz
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> T body(ResultActions resultActions, Class<T> clazz) throws UnsupportedEncodingException {
        String json = resultActions.andReturn().getResponse().getContentAsString();
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
        return new Gson().fromJson(convertedObject.get("body"), clazz);
    }

    /**
     * @param resultActions
     * @param model
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> List<T> list(ResultActions resultActions, Class<T[]> model) throws UnsupportedEncodingException {
        String json = resultActions.andReturn().getResponse().getContentAsString();
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
        return Arrays.asList(new Gson().fromJson(convertedObject.get("body"), model));
    }
}
