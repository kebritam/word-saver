package com.teimour.dictionary.wordsaver.service;

import com.teimour.dictionary.wordsaver.domain.Word;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 15/10/2020
 */

public interface WordService extends CrudService<Word, UUID> {

    Word findByWord(String word);

    Set<Word> findAllByWord(String word);

    void addSynonym(Word word1, Word word2);
}
