package com.lean.vitzi.infrastructure.adapters.out.mysql.repositories;

import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author janez@vitzi.com
 * @version 2022-10-13
 */
@Repository
public interface CategoryRepositoryPort extends JpaRepository<Category, Long> {

    List<Category> findByNameAndIsEnabled(String name, Boolean isEnabled);

}
