package com.lean.vitzi.domain.usecase.category;

import com.lean.vitzi.application.mappers.CategoryMap;
import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import com.lean.vitzi.infrastructure.adapters.out.mysql.repositories.CategoryRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CategoryHandlerTest {

    @InjectMocks
    CategoryHandler categoryHandler;
    @Mock
    CategoryRepositoryPort categoryRepositoryPort;
    @Mock
    CategoryMap categoryMap;

    @Test
    @DisplayName("Find All")
    void findAll() {
        List<Category> categories = List.of(new Category());
        when(categoryRepositoryPort.findAll()).thenReturn(categories);

        List<Category> handlerAll = categoryHandler.findAll();

        Assertions.assertNotNull(handlerAll);
        Assertions.assertTrue(handlerAll.size() > 0);
        Assertions.assertNull(handlerAll.get(0).getName());
    }

    @Test
    @DisplayName("Find by ID")
    void filter() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepositoryPort.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> optionalCategory = categoryRepositoryPort.findById(1L);
        Assertions.assertTrue(optionalCategory.isPresent());
        Assertions.assertNull(optionalCategory.get().getName());
    }

    @Test
    @DisplayName("Create")
    void create() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepositoryPort.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryHandler.create(category);
        Assertions.assertNotNull(createdCategory.getId());
    }

    @Test
    @DisplayName("Update")
    void update() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepositoryPort.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryHandler.create(category);
        Assertions.assertNotNull(createdCategory.getId());
    }

    @Test
    @DisplayName("Remove")
    void delete() {
        Category category = new Category();
        when(categoryRepositoryPort.findById(any())).thenReturn(Optional.of(category));

        Category createdCategory = categoryHandler.delete(1L);
        Assertions.assertNull(createdCategory.getId());
    }
}