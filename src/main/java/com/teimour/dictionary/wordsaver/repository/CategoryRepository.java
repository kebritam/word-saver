package com.teimour.dictionary.wordsaver.repository;

import com.teimour.dictionary.wordsaver.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByCategoryName(String name);
}
