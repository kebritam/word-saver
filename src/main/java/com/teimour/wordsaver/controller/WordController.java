package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.*;
import com.teimour.wordsaver.resources.View;
import com.teimour.wordsaver.service.CategoryService;
import com.teimour.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author kebritam
 * Project word-saver
 * Created on 16/10/2020
 */

@Controller
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;
    private final CategoryService categoryService;

    public WordController(WordService wordService, CategoryService categoryService) {
        this.wordService = wordService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{wordValue}/show")
    public String showWord(Model model, @PathVariable String wordValue){
        Word word=wordService.findByWord(wordValue);
        model.addAttribute("wordSynonyms", Arrays.toString(word.getSynonyms()
                .stream()
                .map(Word::getWordValue)
                .toArray(String[]::new)));
        model.addAttribute("wordAntonyms", Arrays.toString(word.getAntonyms()
                .stream()
                .map(Word::getWordValue)
                .toArray(String[]::new)));
        model.addAttribute("categoryList", Arrays.toString(word.getCategories()
                .stream()
                .map(Category::getCategoryName)
                .toArray(String[]::new)));
        model.addAttribute("word", word);
        return View.WORD;
    }

    @GetMapping("/{wordValue}/edit")
    public String editWord(Model model, @PathVariable String wordValue){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("classes", WordClass.values());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allWords", wordService.findAll());
        return View.WORD_FORM;
    }

    @PostMapping("/{wordValue}/edit")
    public String submitEditWord(@ModelAttribute Word word, BindingResult result, @PathVariable String wordValue){
        if (result.hasErrors()){
            return View.WORD_FORM;
        }

        Word savedWord=wordService.findByWord(wordValue);
        word.setDefinitions(savedWord.getDefinitions());
        word.setUuid(savedWord.getUuid());
        wordService.save(word);

        return "redirect:/word/"+savedWord.getWordValue()+"/show";
    }

    @GetMapping("/new")
    public String newWord(Model model){
        model.addAttribute("word", new Word());
        model.addAttribute("classes", WordClass.values());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allWords", wordService.findAll());

        return View.NEW_WORD_FORM;
    }

    @PostMapping("/new")
    public String submitNewWord(@ModelAttribute Word word, BindingResult result){
        if (result.hasErrors()){
            return View.NEW_WORD_FORM;
        }

        wordService.save(word);

        return "redirect:/word/"+word.getWordValue()+"/show";
    }

}
