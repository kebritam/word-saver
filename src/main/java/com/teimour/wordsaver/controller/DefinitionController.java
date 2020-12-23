package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.domain.WordClass;
import com.teimour.wordsaver.resources.View;
import com.teimour.wordsaver.service.DefinitionService;
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

    public DefinitionController(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @GetMapping("/{uuid}/remove")
    public RedirectView deleteDefinition(@PathVariable UUID uuid, @PathVariable String wordValue) {
        definitionService.deleteDefinition(wordValue, uuid);
        return new RedirectView("/word/" + wordValue + "/show");
    }

    @GetMapping("/new")
    public String addDefinition(Model model, @PathVariable String wordValue){
        model.addAttribute("wordValue", wordValue);
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
        definitionService.addDefinitionToWord(wordValue, definition);
        return "redirect:/word/"+wordValue+"/show";
    }

    @GetMapping("/{uuid}/edit")
    public String editDefinition(Model model, @PathVariable String wordValue, @PathVariable UUID uuid) {
        model.addAttribute("wordValue", wordValue);
        model.addAttribute("classes", WordClass.values());
        model.addAttribute("definition", definitionService.findDefinitionById(wordValue, uuid));
        return View.DEFINITION_FORM;
    }

    @PostMapping("/{uuid}/edit")
    public String submitEditDefinition(@ModelAttribute Definition definition, BindingResult result,
                                       @PathVariable String wordValue, @PathVariable UUID uuid) {

        if (result.hasErrors()){
            return View.DEFINITION_FORM;
        }
        definitionService.editDefinition(wordValue, uuid, definition);
        return "redirect:/word/" + wordValue + "/show";
    }
}
