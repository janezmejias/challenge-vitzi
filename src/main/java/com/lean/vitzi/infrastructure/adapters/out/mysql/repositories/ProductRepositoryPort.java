package com.lean.vitzi.infrastructure.adapters.out.mysql.repositories;

import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author janez@vitzi.com
 * @version 2022-10-11
 */
@Repository
public interface ProductRepositoryPort extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
}
