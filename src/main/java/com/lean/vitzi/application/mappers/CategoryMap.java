package com.lean.vitzi.application.mappers;

import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.time.Instant;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMap {

    @Mappings({
            @Mapping(target = "id", source = "oldCategory.id"),
            @Mapping(target = "name", source = "newCategory.name"),
            @Mapping(target = "description", source = "newCategory.description"),
            @Mapping(target = "isEnabled", source = "newCategory.isEnabled"),
            @Mapping(target = "createdAt", source = "oldCategory.createdAt"),
            @Mapping(target = "updatedAt", expression = "java(getUpdatedAt())")

    })
    Category with(Category oldCategory, Category newCategory);

    @Named("getUpdatedAt")
    default Timestamp getUpdatedAt() {
        return Timestamp.from(Instant.now());
    }

}
