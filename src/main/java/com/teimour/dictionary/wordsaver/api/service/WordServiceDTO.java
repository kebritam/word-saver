package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.api.modelDTO.WordDTO;

import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

public interface WordServiceDTO {

    WordDTO findByWord(String word);

    Set<WordDTO> findAll();

    WordDTO create(WordDTO wordDTO);

    WordDTO update(String name, WordDTO wordDTO);

    void delete(String word, WordDTO wordDTO);
}
