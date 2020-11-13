package com.teimour.wordsaver.api.service;

import com.teimour.wordsaver.mapper.CategoryMapper;
import com.teimour.wordsaver.modelDTO.CategoryDTO;
import com.teimour.wordsaver.domain.Category;
import com.teimour.wordsaver.exception.NotFoundException;
import com.teimour.wordsaver.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Service
public class CategoryServiceAPI implements CategoryServiceDTO {

    private final CategoryRepository categoryRepository;

    public CategoryServiceAPI(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<CategoryDTO> findAll() {
        Set<CategoryDTO> categories=new HashSet<>();
        categoryRepository.findAll().forEach(
                category -> categories.add(CategoryMapper.INSTANCE.categoryToCategoryDTO(category))
        );
        return categories;
    }

    @Override
    public CategoryDTO findByName(String name) {
        Optional<Category> categoryOptional=categoryRepository.findByCategoryName(name);
        if (categoryOptional.isEmpty()){
            throw new NotFoundException("category not found");
        }
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(categoryOptional.get());
    }

    @Override
    public void delete(String name) {
        Optional<Category> categoryOptional=categoryRepository.findByCategoryName(name);
        if (categoryOptional.isEmpty()){
            throw new NotFoundException("category not found");
        }
        categoryRepository.delete(categoryOptional.get());
    }
}
