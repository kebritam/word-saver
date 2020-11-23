package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
 */

@Controller
public class IndexController {

    private final CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/",""})
    public String getIndex(Model model){
        model.addAttribute("categoryList", categoryService.findAll());
        return "index";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
