package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

@Controller
@RequestMapping({"","/","/index"})
public class IndexController {

    private final TagService tagService;

    public IndexController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("tagList", tagService.findAll());
        return "index";
    }
}
