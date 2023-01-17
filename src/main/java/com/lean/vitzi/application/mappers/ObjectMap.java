package com.lean.vitzi.application.mappers;

import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ObjectMap {

    JSONObject with(String response);

    @SneakyThrows
    @AfterMapping
    default void afterMap(@MappingTarget JSONObject jsonObject, String response) {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        JSONObject jsonObjectAux = (JSONObject) jsonParser.parse(response);
        jsonObject.merge(jsonObjectAux);
    }

}
