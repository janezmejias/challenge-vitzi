package com.lean.vitzi.infrastructure.adapters.in.controllers;

import com.lean.vitzi.application.api.ConstantsVitzi;
import com.lean.vitzi.application.response.ResponseBase;
import com.lean.vitzi.infrastructure.adapters.out.mysql.entities.Category;
import com.lean.vitzi.infrastructure.adapters.out.mysql.repositories.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@RestController
@RequestMapping(value = ConstantsVitzi.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @GetMapping
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok(new ResponseBase<>().onSuccess(
                categoryRepositoryPort.findAll()
        ));
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity with(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepositoryPort.findById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(new ResponseBase<>().onSuccess(
                    optionalCategory.get()
            ));
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity create(@RequestBody Category model) {
        return ResponseEntity.ok(new ResponseBase<>().onSuccess(
                categoryRepositoryPort.save(model)
        ));
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity update(@RequestBody Category model) {
        return ResponseEntity.ok(new ResponseBase<>().onSuccess(
                categoryRepositoryPort.save(model)
        ));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        categoryRepositoryPort.deleteById(id);
        return ResponseEntity.ok(new ResponseBase<>().onSuccess(""));
    }

}
