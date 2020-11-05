package com.teimour.dictionary.wordsaver.api.controller;

import com.teimour.dictionary.wordsaver.api.service.CategoryServiceDTO;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@RestController
public class CategoryApiController {

    private final CategoryServiceDTO categoryService;

    public CategoryApiController(CategoryServiceDTO categoryService) {
        this.categoryService = categoryService;
    }
}
