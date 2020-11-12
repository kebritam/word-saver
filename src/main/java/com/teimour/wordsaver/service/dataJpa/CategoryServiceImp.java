package com.teimour.wordsaver.service.dataJpa;

import com.teimour.wordsaver.domain.Category;
import com.teimour.wordsaver.exception.NotFoundException;
import com.teimour.wordsaver.repository.CategoryRepository;
import com.teimour.wordsaver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
 */

@Slf4j
@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(UUID uuid) {
        Optional<Category> tagOptional= categoryRepository.findById(uuid);
        if (tagOptional.isPresent()){
            return tagOptional.get();
        } else{
            throw new NotFoundException("category not found");
        }
    }

    @Override
    public Set<Category> findAll() {
        return Set.copyOf(categoryRepository.findAll());
    }

    @Override
    public Category findByName(String name) {
        Optional<Category> optionalCategory= categoryRepository.findByCategoryName(name);
        if (optionalCategory.isEmpty()){
            throw new NotFoundException("category not found");
        }
        return optionalCategory.get();
    }

    @Override
    public Category save(Category object) {
        return categoryRepository.save(object);
    }

    @Override
    public void delete(Category object) {
        categoryRepository.delete(object);
        log.info("category deleted");
    }

    @Override
    public void deleteById(UUID uuid) {
        categoryRepository.deleteById(uuid);
        log.info("category deleted");
    }
}
