package com.lean.vitzi.application.mappers;

import lombok.SneakyThrows;
import net.minidev.json.JSONArray;
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
public interface ListMap {

    JSONArray with(String response);

    @SneakyThrows
    @AfterMapping
    default void afterMap(@MappingTarget JSONArray jsonArray, String response) {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        JSONArray jsonObjectAux = (JSONArray) jsonParser.parse(response);
        jsonArray.merge(jsonObjectAux);
    }

}
