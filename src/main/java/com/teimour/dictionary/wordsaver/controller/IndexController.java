package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

@Controller
@RequestMapping({"","/","/index"})
public class IndexController {

    private final CategoryService categoryService;

    public IndexController(@Qualifier("categoryServiceImp") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("categoryList", categoryService.findAll());
        return "index";
    }
}
