package com.lean.vitzi.domain.usecase.category;

import com.lean.vitzi.application.mappers.CategoryMap;
import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import com.lean.vitzi.infrastructure.adapters.out.mysql.repositories.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final CategoryRepositoryPort categoryRepositoryPort;
    private final CategoryMap categoryMap;

    public List<Category> findAll() {
        return categoryRepositoryPort.findAll();
    }

    public List<Category> filter(Category category) {
        return categoryRepositoryPort.findAll();
    }

    public Category create(Category category) {
        return categoryRepositoryPort.save(category);
    }

    public Category update(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepositoryPort.findById(id);
        if (optionalCategory.isPresent()) {
            Category with = categoryMap.with(optionalCategory.get(), category);
            categoryRepositoryPort.save(with);
            return with;
        }
        throw new RuntimeException("No available");
    }

    public Category delete(Long id) {
        Optional<Category> optionalCategory = categoryRepositoryPort.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepositoryPort.deleteById(id);
            return optionalCategory.get();
        }
        throw new RuntimeException("No available to delete");
    }
}
