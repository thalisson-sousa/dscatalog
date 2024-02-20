package com.devthalisson.dscatalog.services;

import com.devthalisson.dscatalog.dto.CategoryDTO;
import com.devthalisson.dscatalog.entities.Category;
import com.devthalisson.dscatalog.repositories.CategoryRepository;
import com.devthalisson.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        return listDto;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
            Category obj = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
            return new CategoryDTO(obj);
    }
}
