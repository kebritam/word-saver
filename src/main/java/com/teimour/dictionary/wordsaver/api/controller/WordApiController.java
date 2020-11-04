package com.teimour.dictionary.wordsaver.api.controller;

import com.teimour.dictionary.wordsaver.service.WordService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@RestController
public class WordApiController {

    private final WordService wordService;

    public WordApiController(@Qualifier("wordServiceAPI") WordService wordService) {
        this.wordService = wordService;
    }
}
