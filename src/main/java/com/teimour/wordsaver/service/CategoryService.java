package com.teimour.wordsaver.service;

import com.teimour.wordsaver.domain.Category;

import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
 */

public interface CategoryService extends CrudService<Category, UUID> {

    Category findByName(String name);
}
