package com.teimour.wordsaver.api.service;

import com.teimour.wordsaver.modelDTO.CategoryDTO;


import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

public interface CategoryServiceDTO {

    Set<CategoryDTO> findAll();

    CategoryDTO findByName(String name);

    void delete(String name);
}
