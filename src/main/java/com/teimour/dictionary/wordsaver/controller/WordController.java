package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.domain.WordClasses;
import com.teimour.dictionary.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{wordValue}/edit")
    public String editWord(Model model, @PathVariable String wordValue){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("classes", WordClasses.values());
        return "wordForm";
    }

    @PostMapping("/{wordValue}/edit")
    public String submitEditWord(@ModelAttribute Word word, @PathVariable String wordValue){
        Word savedWord=wordService.findByWord(wordValue);
        savedWord.setWordValue(word.getWordValue());
        savedWord.setNotes(word.getNotes());
        savedWord.setPhonetic(word.getPhonetic());
        savedWord.setWordClasses(word.getWordClasses());

//        savedWord.setAntonyms(word.getAntonyms());// TODO: 10/23/2020 should be implemented in wordForm.html
//        savedWord.setSynonyms(word.getSynonyms());// TODO: 10/23/2020 should be implemented in wordForm.html
//        savedWord.setDefinitions(word.getDefinitions());// TODO: 10/23/2020 should be implemented in wordForm.html

        wordService.save(savedWord);

        return "redirect:/word/"+savedWord.getWordValue()+"/show";
    }

    @GetMapping("/new")
    public String newWord(Model model){
        model.addAttribute("word", new Word());
        model.addAttribute("classes", WordClasses.values());
        return "wordForm";
    }

    @PostMapping("/new")
    public String submitNewWord(@ModelAttribute Word word){
        wordService.save(word);
        return "redirect:/word/"+word.getWordValue()+"/show";
    }

}
