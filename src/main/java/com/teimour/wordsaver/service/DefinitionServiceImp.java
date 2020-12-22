package com.teimour.wordsaver.service;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 21/12/2020
 */

@Service
public class DefinitionServiceImp implements DefinitionService {

    @Override
    public Definition findDefinitionById(Word word, UUID uuid) {
        return word.getDefinitions().stream()
                .filter(definition -> definition.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteDefinition(Word relatedWord, UUID uuid) {
        relatedWord.getDefinitions().remove(findDefinitionById(relatedWord, uuid));
    }

}