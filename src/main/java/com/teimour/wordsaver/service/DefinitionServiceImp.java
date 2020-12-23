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

    private final WordService wordService;

    public DefinitionServiceImp(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public Definition findDefinitionById(Word word, UUID uuid) {
        return word.getDefinitions().stream()
                .filter(definition -> definition.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Definition findDefinitionById(String wordValue, UUID uuid) {
        Word relatedWord = wordService.findByWord(wordValue);
        return findDefinitionById(relatedWord, uuid);
    }

    @Override
    public void deleteDefinition(Word word, UUID uuid) {
        word.getDefinitions().remove(findDefinitionById(word, uuid));
        wordService.save(word);
    }

    @Override
    public void deleteDefinition(String wordValue, UUID uuid) {
        Word relatedWord = wordService.findByWord(wordValue);
        deleteDefinition(relatedWord, uuid);
    }

    @Override
    public void addDefinitionToWord(Word word, Definition definition) {
        word.getDefinitions().add(definition);
        wordService.save(word);
    }

    @Override
    public void addDefinitionToWord(String wordValue, Definition definition) {
        Word relatedWord = wordService.findByWord(wordValue);
        addDefinitionToWord(relatedWord, definition);
    }

    @Override
    public void editDefinition(Word word, UUID uuid, Definition definition) {
        Definition relatedDefinition = findDefinitionById(word, uuid);
        relatedDefinition.setExamples(definition.getExamples());
        relatedDefinition.setWordClass(definition.getWordClass());
        relatedDefinition.setDefinitionValue(definition.getDefinitionValue());
        wordService.save(word);
    }

    @Override
    public void editDefinition(String wordValue,UUID uuid, Definition definition) {
        Word relatedWord = wordService.findByWord(wordValue);
        editDefinition(relatedWord, uuid, definition);
    }
}