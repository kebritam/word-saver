package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.exception.NotFoundException;
import com.teimour.dictionary.wordsaver.repository.CategoryRepository;
import com.teimour.dictionary.wordsaver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
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
        log.info("tag deleted");
    }

    @Override
    public void deleteById(UUID uuid) {
        categoryRepository.deleteById(uuid);
        log.info("tag deleted");
    }
}
