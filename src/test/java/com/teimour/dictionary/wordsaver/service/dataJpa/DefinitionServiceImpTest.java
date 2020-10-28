package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Definition;
import com.teimour.dictionary.wordsaver.domain.WordClasses;
import com.teimour.dictionary.wordsaver.exception.NotFoundException;
import com.teimour.dictionary.wordsaver.repository.DefinitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author kebritam
 * @project word-saver
 * @date 28/10/2020
 */

@ExtendWith(MockitoExtension.class)
class DefinitionServiceImpTest {

    @Mock
    DefinitionRepository definitionRepository;

    @InjectMocks
    DefinitionServiceImp definitionServiceImp;

    Definition definition1;
    Definition definition2;

    @BeforeEach
    void setUp() {
        definition1=Definition.builder()
                .definitionValue("The FIRST definition of tests")
                .wordClass(WordClasses.ADJECTIVE).build();

        definition2=Definition.builder()
                .definitionValue("The SECOND definition of tests")
                .wordClass(WordClasses.VERB).build();
    }

    @Test
    void findByIdTrue() {
        when(definitionRepository.findById(any())).thenReturn(Optional.of(definition1));
        Definition savedDefinition= definitionServiceImp.findById(any());
        assertEquals(definition1, savedDefinition);
        verify(definitionRepository).findById(any());
    }

    @Test
    void findByIdFalse() {
        when(definitionRepository.findById(any())).thenReturn(Optional.empty());
        NotFoundException exception= assertThrows(NotFoundException.class,
                ()-> definitionServiceImp.findById(any()));

        assertEquals(exception.getMessage(), "definition not found");
        verify(definitionRepository).findById(any());
    }

    @Test
    void findAll() {
        when(definitionRepository.findAll()).thenReturn(List.of(definition1, definition2));
        assertEquals(definitionServiceImp.findAll(), Set.of(definition1, definition2));
    }

    @Test
    void save() {
        Definition definition=Definition.builder().definitionValue("saveDef").build();
        when(definitionRepository.save(any())).thenReturn(definition);
        Definition savedDefinition=definitionServiceImp.save(any());
        assertEquals(savedDefinition, definition);
    }

    @Test
    void updateBySave() {
        when(definitionRepository.save(any())).thenReturn(definition1);
        Definition savedDefinition=definitionServiceImp.save(any());
        definition1.setDefinitionValue("changed");
        definition1.setWordClass(WordClasses.NOUN);
        assertEquals(savedDefinition, definition1);
    }

    @Test
    void delete() {
        definitionServiceImp.delete(definition2);
        verify(definitionRepository).delete(any());
    }

    @Test
    void deleteById() {
        definitionServiceImp.deleteById(definition2.getUuid());
        verify(definitionRepository).deleteById(any());
    }
}