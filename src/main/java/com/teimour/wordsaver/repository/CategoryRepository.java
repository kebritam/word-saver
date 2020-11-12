package com.teimour.wordsaver.repository;

import com.teimour.wordsaver.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
 */

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByCategoryName(String name);
}
