package com.teimour.dictionary.wordsaver.api.controller;

import com.teimour.dictionary.wordsaver.api.modelDTO.WordDTO;
import com.teimour.dictionary.wordsaver.api.modelDTO.WordListDTO;
import com.teimour.dictionary.wordsaver.api.service.WordServiceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@RestController
@RequestMapping("/word")
public class WordApiController {

    private final WordServiceDTO wordService;

    public WordApiController(WordServiceDTO wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WordListDTO getAllWords(){
        return new WordListDTO(wordService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordDTO createWord(@RequestBody WordDTO wordDTO){
        return wordService.create(wordDTO);
    }

    @GetMapping("/{value}")
    @ResponseStatus(HttpStatus.OK)
    public WordDTO getWord(@PathVariable String value){
        return wordService.findByWord(value);
    }

    @PutMapping("/{value}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public WordDTO updateWord(@RequestBody WordDTO wordDTO, @PathVariable String value){
        return wordService.update(value, wordDTO);
    }

    @DeleteMapping("/{value}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWord(@PathVariable String value){
        wordService.delete(value);
    }
}
