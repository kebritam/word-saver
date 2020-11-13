package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.domain.WordClasses;
import com.teimour.wordsaver.service.CategoryService;
import com.teimour.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("categoryList", word.getCategories());
        model.addAttribute("word", word);
        return "word";
    }

    @GetMapping("/{wordValue}/edit")
    public String editWord(Model model, @PathVariable String wordValue){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("classes", WordClasses.values());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allWords", wordService.findAll());
        return "wordForm";
    }

    @PostMapping("/{wordValue}/edit")
    public String submitEditWord(@ModelAttribute Word word, BindingResult result, @PathVariable String wordValue){

        if (result.hasErrors()){
            return "wordForm";
        }

        Word savedWord=wordService.findByWord(wordValue);
        savedWord.setWordValue(word.getWordValue());
        savedWord.setNotes(word.getNotes());
        savedWord.setPhonetic(word.getPhonetic());
        savedWord.setWordClasses(word.getWordClasses());

        savedWord.setAntonyms(word.getAntonyms());
        savedWord.setSynonyms(word.getSynonyms());
        savedWord.setDefinitions(word.getDefinitions());

        wordService.save(savedWord);

        return "redirect:/word/"+savedWord.getWordValue()+"/show";
    }

    @GetMapping("/new")
    public String newWord(Model model){
        model.addAttribute("word", new Word());
        model.addAttribute("classes", WordClasses.values());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allWords", wordService.findAll());
        return "wordForm";
    }

    @PostMapping("/new")
    public String submitNewWord(@ModelAttribute Word word, BindingResult result){

        if (result.hasErrors()){
            return "wordForm";
        }

        wordService.save(word);
        return "redirect:/word/"+word.getWordValue()+"/show";
    }

}