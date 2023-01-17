package com.lean.vitzi.infrastructure.adapters.in.controllers;

import com.lean.vitzi.domain.usecase.category.CategoryHandler;
import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryGraphController {

    private final CategoryHandler categoryHandler;

    @QueryMapping
    List<Category> findAll() {
        return categoryHandler.findAll();
    }

    @QueryMapping
    List<Category> filterBy(@Argument Category category) {
        return categoryHandler.filter(category);
    }

    @MutationMapping
    Category create(@Argument Category category) {
        return categoryHandler.create(category);
    }

    @MutationMapping
    Category update(@Argument Long id, @Argument Category category) {
        return categoryHandler.update(id, category);
    }

    @MutationMapping
    Category delete(@Argument Long id) {
        return categoryHandler.delete(id);
    }
}
