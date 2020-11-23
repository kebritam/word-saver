package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.domain.WordClass;
import com.teimour.wordsaver.service.DefinitionService;
import com.teimour.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 16/10/2020
 */

@Controller
@RequestMapping("/word/{wordValue}/definition")
public class DefinitionController {

    private final DefinitionService definitionService;
    private final WordService wordService;

    public DefinitionController(DefinitionService definitionService, WordService wordService) {

        this.definitionService = definitionService;
        this.wordService = wordService;
    }

    @GetMapping("/{uuid}/remove")
    public String deleteDefinition(@PathVariable UUID uuid, @PathVariable String wordValue){
        definitionService.deleteById(uuid);
        return "redirect:/word/"+wordValue+"/show";
    }

    @GetMapping("/new")
    public String addDefinition(Model model, @PathVariable String wordValue){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("definition", new Definition());
        model.addAttribute("classes", WordClass.values());
        return "definitionForm";
    }

    @PostMapping("/new")
    public String submitAddDefinition(@ModelAttribute Definition definition, BindingResult result,
                                      @PathVariable String wordValue){

        if (result.hasErrors()){
            return "definitionForm";
        }

        Word savedWord=wordService.findByWord(wordValue);
        savedWord.getDefinitions().add(definition);
        wordService.save(savedWord);
        return "redirect:/word/"+wordValue+"/show";
    }

    @GetMapping("/{uuid}/edit")
    public String editDefinition(Model model, @PathVariable String wordValue, @PathVariable UUID uuid){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("definition", definitionService.findById(uuid));
        model.addAttribute("classes", WordClass.values());
        return "definitionForm";
    }

    @PostMapping("/{uuid}/edit")
    public String submitEditDefinition(@ModelAttribute Definition definition, BindingResult result,
                                       @PathVariable String wordValue, @PathVariable UUID uuid){

        if (result.hasErrors()){
            return "definitionForm";
        }

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
