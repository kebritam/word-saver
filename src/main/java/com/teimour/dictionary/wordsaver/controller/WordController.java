package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Controller
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/{wordValue}/show")
    public String showWord(Model model, @PathVariable String wordValue){

        model.addAttribute("word", wordService.findByWord(wordValue));
        return "word";
    }
}
