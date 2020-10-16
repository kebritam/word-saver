package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.ExampleService;
import org.springframework.stereotype.Controller;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Controller
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
}
