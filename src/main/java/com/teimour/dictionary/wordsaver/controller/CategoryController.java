package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

@Slf4j
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
        model.addAttribute("words", category.getWords());
        return "category";
    }

    @GetMapping("/{name}/edit")
    public String editCategory(Model model, @PathVariable String name){
        model.addAttribute("category", categoryService.findByName(name));
        return "categoryForm";
    }

    @PostMapping("{name}/edit")
    public String submitEditCategory(@ModelAttribute Category category, @PathVariable String name){
        Category savedCategory=categoryService.findByName(name);
        savedCategory.setCategoryName(category.getCategoryName());
        categoryService.save(savedCategory);

        return "redirect:/";
    }

    @GetMapping("/new")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @PostMapping("/new")
    public String submitNewCategory(@ModelAttribute Category category){
        categoryService.save(category);
        log.debug(category.getCategoryName());
        log.debug(category.getId().toString());
        return "redirect:/";
    }
}
