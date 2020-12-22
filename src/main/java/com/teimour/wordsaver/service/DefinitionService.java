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
    Definition findDefinitionById(Word word, UUID uuid);

    void deleteDefinition(Word relatedWord, UUID uuid);
}
