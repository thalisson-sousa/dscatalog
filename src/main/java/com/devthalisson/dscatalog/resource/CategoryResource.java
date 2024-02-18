package com.devthalisson.dscatalog.resource;

import com.devthalisson.dscatalog.entities.Category;
import com.devthalisson.dscatalog.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryServices services;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }
}
