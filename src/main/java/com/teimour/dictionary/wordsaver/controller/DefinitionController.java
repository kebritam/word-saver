package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.domain.Definition;
import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.domain.WordClasses;
import com.teimour.dictionary.wordsaver.service.DefinitionService;
import com.teimour.dictionary.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Controller
public class DefinitionController {

    private final DefinitionService definitionService;
    private final WordService wordService;

    public DefinitionController(DefinitionService definitionService, WordService wordService) {
        this.definitionService = definitionService;
        this.wordService = wordService;
    }

    @GetMapping("/word/{wordValue}/definition/{uuid}/remove")
    public String deleteDefinition(@PathVariable UUID uuid, @PathVariable String wordValue){
        definitionService.deleteById(uuid);
        return "redirect:/word/"+wordValue+"/show";
    }

    @GetMapping("/word/{wordValue}/definition/new")
    public String addDefinition(Model model, @PathVariable String wordValue){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("definition", new Definition());
        model.addAttribute("classes", WordClasses.values());
        return "definitionForm";
    }

    @PostMapping("/word/{wordValue}/definition/new")
    public String submitAddDefinition(@ModelAttribute Definition definition, @PathVariable String wordValue){
        Word savedWord=wordService.findByWord(wordValue);
        savedWord.getDefinitions().add(definition);
        wordService.save(savedWord);
        return "redirect:/word/"+wordValue+"/show";
    }

    @GetMapping("/word/{wordValue}/definition/{uuid}/edit")
    public String editDefinition(Model model, @PathVariable String wordValue, @PathVariable UUID uuid){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("definition", definitionService.findById(uuid));
        model.addAttribute("classes", WordClasses.values());
        return "definitionForm";
    }

    @PostMapping("/word/{wordValue}/definition/{uuid}/edit")
    public String submitEditDefinition(@ModelAttribute Definition definition, @PathVariable String wordValue,
                                       @PathVariable UUID uuid){

        Word savedWord=wordService.findByWord(wordValue);
        Definition savedDefinition=definitionService.findById(uuid);
        savedDefinition.setDefinitionValue(definition.getDefinitionValue());
        savedDefinition.setWordClass(definition.getWordClass());
        savedDefinition.setExamples(definition.getExamples());
        savedWord.getDefinitions().add(savedDefinition);
        wordService.save(savedWord);
        return "redirect:/word/"+wordValue+"/show";
    }
}
