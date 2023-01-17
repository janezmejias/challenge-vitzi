package com.lean.vitzi.infrastructure.adapters.in.controllers;

import com.lean.vitzi.application.api.ConstantsVitzi;
import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import com.lean.vitzi.infrastructure.adapters.out.mysql.repositories.CategoryRepositoryPort;
import com.lean.vitzi.infrastructure.helper.PerformMockMvcComponent;
import com.lean.vitzi.infrastructure.helper.ResultActionsHelper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class CategoryGraphControllerTest {

    @Autowired
    private PerformMockMvcComponent performMockMvcComponent;
    @Autowired
    private CategoryRepositoryPort categoryRepositoryPort;
    private final String PATCH_MAPPING = ConstantsVitzi.CATEGORY;

    @Test
    void findAll() throws Exception {
        performMockMvcComponent.performGetAll(PATCH_MAPPING);
    }


    @Test
    void filterBy() throws Exception {
        ResultActions resultActions = performMockMvcComponent.performGetAll(PATCH_MAPPING);
        List<Category> list = ResultActionsHelper.list(resultActions, Category[].class);

        Category category = list.get(0);
        performMockMvcComponent.performGetOne(PATCH_MAPPING, category.getId());
    }

    @Test
    void create() throws Exception {
        Category category = new Category();
        category.setName("Cars");
        category.setDescription("Cars descriptions");

        performMockMvcComponent.performAdd(PATCH_MAPPING + "/create", category);
    }

    @Test
    void update() throws Exception {
        ResultActions resultActions = performMockMvcComponent.performGetAll(PATCH_MAPPING);
        List<Category> list = ResultActionsHelper.list(resultActions, Category[].class);

        Category category = list.get(0);
        category.setDescription(" - edit - last - ");
        category.setCreatedAt(null);
        category.setUpdatedAt(null);
        performMockMvcComponent.performEdit(PATCH_MAPPING + "/update", category);
    }

    @Test
    void delete() throws Exception {
        ResultActions resultActions = performMockMvcComponent.performGetAll(PATCH_MAPPING);
        List<Category> list = ResultActionsHelper.list(resultActions, Category[].class);

        Category category = list.get(0);
        performMockMvcComponent.performDelete(PATCH_MAPPING + "/" + category.getId());
    }

}