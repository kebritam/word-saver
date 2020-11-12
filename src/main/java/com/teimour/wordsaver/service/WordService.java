package com.teimour.wordsaver.service;

import com.teimour.wordsaver.domain.Word;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 15/10/2020
 */

public interface WordService extends CrudService<Word, UUID> {

    Word findByWord(String word);

    Set<Word> findAllByWord(String word);

    void addSynonym(Word processedWord, Word newWord);

    void addAntonym(Word processedWord, Word newWord);
}
