package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Category;
import com.teimour.wordsaver.resources.View;
import com.teimour.wordsaver.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
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
        model.addAttribute("words", category.getWords());

        return View.CATEGORY;
    }

    @GetMapping("/{name}/edit")
    public String editCategory(Model model, @PathVariable String name){
        model.addAttribute("category", categoryService.findByName(name));

        return View.CATEGORY_FORM;
    }

    @PostMapping("/{name}/edit")
    public String submitEditCategory(@Valid @ModelAttribute Category category, BindingResult result,
                                     @PathVariable String name){

        if (result.hasErrors()){
            return View.CATEGORY_FORM;
        }

        Category savedCategory=categoryService.findByName(name);
        category.setId(savedCategory.getId());
        categoryService.save(category);

        return "redirect:/";
    }

    @GetMapping("/new")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());

        return View.NEW_CATEGORY_FORM;
    }

    @PostMapping("/new")
    public String submitNewCategory(@Valid @ModelAttribute Category category, BindingResult result){
        if (result.hasErrors()){
            return View.NEW_CATEGORY_FORM;
        }

        categoryService.save(category);

        return "redirect:/";
    }
}
