package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.DefinitionService;
import org.springframework.stereotype.Controller;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Controller
public class DefinitionController {

    private final DefinitionService definitionService;

    public DefinitionController(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }
}
