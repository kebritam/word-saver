package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.api.modelDTO.CategoryDTO;


import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

public interface CategoryServiceDTO {

    Set<CategoryDTO> findAll();

    CategoryDTO findByName(String name);

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(String name, CategoryDTO categoryDTO);

    void delete(String name);
}
