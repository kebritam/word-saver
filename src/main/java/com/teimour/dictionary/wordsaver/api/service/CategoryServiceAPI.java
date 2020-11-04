package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.repository.CategoryRepository;
import com.teimour.dictionary.wordsaver.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Service
public class CategoryServiceAPI implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceAPI(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByName(String name) {
        return null;
    }

    @Override
    public Category findById(UUID uuid) {
        return null;
    }

    @Override
    public Set<Category> findAll() {
        return null;
    }

    @Override
    public Category save(Category object) {
        return null;
    }

    @Override
    public void delete(Category object) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }
}
