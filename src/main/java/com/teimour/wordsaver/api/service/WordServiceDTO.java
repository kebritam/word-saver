package com.teimour.wordsaver.api.service;

import com.teimour.wordsaver.modelDTO.WordDTO;

import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

public interface WordServiceDTO {

    WordDTO findByWord(String word);

    Set<WordDTO> findAll();

    WordDTO create(WordDTO wordDTO);

    WordDTO update(String name, WordDTO wordDTO);

    void delete(String word);
}
