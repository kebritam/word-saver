package com.teimour.dictionary.wordsaver.api.controller;

import com.teimour.dictionary.wordsaver.api.service.WordServiceDTO;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@RestController
public class WordApiController {

    private final WordServiceDTO wordService;

    public WordApiController(WordServiceDTO wordService) {
        this.wordService = wordService;
    }
}
