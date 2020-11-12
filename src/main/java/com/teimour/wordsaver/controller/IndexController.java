package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
 */

@Controller
@RequestMapping({"","/","/index"})
public class IndexController {

    private final CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("categoryList", categoryService.findAll());
        return "index";
    }
}
