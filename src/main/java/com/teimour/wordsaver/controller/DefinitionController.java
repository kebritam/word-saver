package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.domain.WordClass;
import com.teimour.wordsaver.resources.View;
import com.teimour.wordsaver.service.DefinitionService;
import com.teimour.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView deleteDefinition(@PathVariable UUID uuid, @PathVariable String wordValue) {
        Word relatedWord = wordService.findByWord(wordValue);
        definitionService.deleteDefinition(relatedWord, uuid);
        wordService.save(relatedWord);

        return new RedirectView("/word/" + wordValue + "/show");
    }

    @GetMapping("/new")
    public String addDefinition(Model model, @PathVariable String wordValue){
        model.addAttribute("word", wordService.findByWord(wordValue));
        model.addAttribute("definition", new Definition());
        model.addAttribute("classes", WordClass.values());
        return View.NEW_DEFINITION_FORM;
    }

    @PostMapping("/new")
    public String submitAddDefinition(@ModelAttribute Definition definition, BindingResult result,
                                      @PathVariable String wordValue){

        if (result.hasErrors()){
            return View.NEW_DEFINITION_FORM;
        }

        Word savedWord=wordService.findByWord(wordValue);
        savedWord.getDefinitions().add(definition);
        wordService.save(savedWord);
        return "redirect:/word/"+wordValue+"/show";
    }

    @GetMapping("/{uuid}/edit")
    public String editDefinition(Model model, @PathVariable String wordValue, @PathVariable UUID uuid) {
        Word relatedWord = wordService.findByWord(wordValue);

        model.addAttribute("word", relatedWord);
        model.addAttribute("classes", WordClass.values());
        model.addAttribute("definition", definitionService.findDefinitionById(relatedWord, uuid));

        return View.DEFINITION_FORM;
    }

    @PostMapping("/{uuid}/edit")
    public String submitEditDefinition(@ModelAttribute Definition definition, BindingResult result,
                                       @PathVariable String wordValue, @PathVariable UUID uuid) {

        if (result.hasErrors()){
            return View.DEFINITION_FORM;
        }

        Word relatedWord = wordService.findByWord(wordValue);
        Definition presentDefinition = definitionService.findDefinitionById(relatedWord, uuid);

        presentDefinition.setDefinitionValue(definition.getDefinitionValue());
        presentDefinition.setWordClass(definition.getWordClass());
        presentDefinition.setExamples(definition.getExamples());

        wordService.save(relatedWord);

        return "redirect:/word/" + wordValue + "/show";
    }
}
