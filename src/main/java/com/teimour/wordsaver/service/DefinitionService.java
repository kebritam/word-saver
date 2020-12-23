package com.teimour.wordsaver.service;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.domain.Word;

import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 21/12/2020
 */

public interface DefinitionService {

    Definition findDefinitionById(String wordValue, UUID uuid);
    Definition findDefinitionById(Word relatedWord, UUID uuid);

    void deleteDefinition(String wordValue, UUID uuid);
    void deleteDefinition(Word word, UUID uuid);

    void addDefinitionToWord(Word word, Definition definition);
    void addDefinitionToWord(String wordValue, Definition definition);

    void editDefinition(Word word, UUID uuid, Definition definition);
    void editDefinition(String wordValue,UUID uuid, Definition definition);
}
