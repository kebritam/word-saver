package com.teimour.dictionary.wordsaver.api.controller;

import com.teimour.dictionary.wordsaver.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(@Qualifier("categoryServiceAPI") CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
