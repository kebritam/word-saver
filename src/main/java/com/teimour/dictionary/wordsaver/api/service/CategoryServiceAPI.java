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
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category tempCategory=CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTO);
        tempCategory = categoryRepository.save(tempCategory);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(tempCategory);
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
        Category mappedCategory=CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTO);
        Category category=optionalCategory.get();
        category.setCategoryName(mappedCategory.getCategoryName());
        category.setWords(mappedCategory.getWords());
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(categoryRepository.save(category));
    }
}
