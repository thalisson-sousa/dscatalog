package com.devthalisson.dscatalog.services;

import com.devthalisson.dscatalog.dto.CategoryDTO;
import com.devthalisson.dscatalog.entities.Category;
import com.devthalisson.dscatalog.repositories.CategoryRepository;
import com.devthalisson.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            Category obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
            return new CategoryDTO(obj);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category obj = repository.getReferenceById(id);
            obj.setName(dto.getName());
            obj = repository.save(obj);
            return new CategoryDTO(obj);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
    }
}
