package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.api.mapper.CategoryMapper;
import com.teimour.dictionary.wordsaver.api.modelDTO.CategoryDTO;
import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.exception.NotFoundException;
import com.teimour.dictionary.wordsaver.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Service
public class CategoryServiceAPI implements CategoryServiceDTO {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceAPI(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Set<CategoryDTO> findAll() {
        Set<CategoryDTO> categories=new HashSet<>();
        categoryRepository.findAll().forEach(
                category -> categories.add(categoryMapper.categoryToCategoryDTO(category))
        );
        return categories;
    }

    @Override
    public CategoryDTO findByName(String name) {
        Optional<Category> categoryOptional=categoryRepository.findByCategoryName(name);
        if (categoryOptional.isEmpty()){
            throw new NotFoundException("category not found");
        }
        return categoryMapper.categoryToCategoryDTO(categoryOptional.get());
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category tempCategory=categoryMapper.categoryDTOToCategory(categoryDTO);
        tempCategory = categoryRepository.save(tempCategory);
        return categoryMapper.categoryToCategoryDTO(tempCategory);
    }

    @Override
    public void delete(String name) {
        Optional<Category> categoryOptional=categoryRepository.findByCategoryName(name);
        if (categoryOptional.isEmpty()){
            throw new NotFoundException("category not found");
        }
        categoryRepository.delete(categoryOptional.get());
    }

    @Override
    public CategoryDTO update(String name, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory=categoryRepository.findByCategoryName(name);
        if (optionalCategory.isEmpty()){
            throw new NotFoundException("category not found");
        }
        Category mappedCategory=categoryMapper.categoryDTOToCategory(categoryDTO);
        Category category=optionalCategory.get();
        category.setCategoryName(mappedCategory.getCategoryName());
        category.setWords(mappedCategory.getWords());
        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }
}
