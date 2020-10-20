package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{name}/show")
    public String showCategory(Model model, @PathVariable String name){
        Category category=categoryService.findByName(name);
        model.addAttribute("category", category);
        return "category";
    }
}
