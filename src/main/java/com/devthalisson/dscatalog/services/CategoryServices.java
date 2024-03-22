package com.devthalisson.dscatalog.services;

import com.devthalisson.dscatalog.dto.CategoryDTO;
import com.devthalisson.dscatalog.entities.Category;
import com.devthalisson.dscatalog.repositories.CategoryRepository;
import com.devthalisson.dscatalog.services.exceptions.DatabaseException;
import com.devthalisson.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
        Page<Category> list = repository.findAll(pageRequest);
        Page<CategoryDTO> listDto = list.map(x -> new CategoryDTO(x));
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
