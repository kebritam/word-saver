package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.WordService;
import org.springframework.stereotype.Controller;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Controller
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }
}
