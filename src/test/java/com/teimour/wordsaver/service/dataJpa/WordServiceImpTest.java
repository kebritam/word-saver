package com.teimour.wordsaver.service.dataJpa;

import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.exception.NotFoundException;
import com.teimour.wordsaver.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

/**
 * @author kebritam
 * Project word-saver
 * Created on 28/10/2020
 */

@ExtendWith(MockitoExtension.class)
class WordServiceImpTest {

    @Mock
    WordRepository wordRepository;

    @InjectMocks
    WordServiceImp wordServiceImp;

    Word word1;
    Word word2;
    Word word3;

    @BeforeEach
    void setUp() {
        word1=Word.builder()
                .wordValue("test")
                .synonyms(new HashSet<>())
                .antonyms(new HashSet<>())
                .build();
        word2=Word.builder()
                .wordValue("test methode has")
                .synonyms(new HashSet<>())
                .antonyms(new HashSet<>())
                .build();
        word3=Word.builder()
                .wordValue("test methode has implemented")
                .synonyms(new HashSet<>())
                .antonyms(new HashSet<>())
                .build();

    }

    @Test
    void findByIdTrue() {
        when(wordRepository.findById(any())).thenReturn(Optional.of(word2));
        Word word=wordServiceImp.findById(word1.getUuid());
        verify(wordRepository).findById(any());
        assertEquals(word, word2);
    }

    @Test
    void findByIdFalse() {
        when(wordRepository.findById(any())).thenReturn(Optional.empty());
        NotFoundException exception= assertThrows(NotFoundException.class,
                ()-> wordServiceImp.findById(any()));

        assertEquals("word not found",exception.getMessage());
        verify(wordRepository).findById(any());
    }

    @Test
    void findByWordTrue() {
        when(wordRepository.findByWordValueIgnoreCase(anyString())).thenReturn(Optional.of(word1));
        Word savedWord=wordServiceImp.findByWord("TeSt");
        assertEquals(savedWord, word1);
        when(wordRepository.findByWordValueIgnoreCase(anyString())).thenReturn(Optional.of(word2));
        savedWord=wordServiceImp.findByWord("test methode has");
        assertEquals(savedWord, word2);
    }

    @Test
    void findByWordFalse() {
        when(wordRepository.findByWordValueIgnoreCase(anyString())).thenReturn(Optional.empty());
        NotFoundException exception= assertThrows(NotFoundException.class,
                ()-> wordServiceImp.findByWord(anyString()));

        assertEquals("word not found",exception.getMessage());
        verify(wordRepository).findByWordValueIgnoreCase(anyString());
    }

    @Test
    void findAll() {
        when(wordRepository.findAll()).thenReturn(List.of(word1, word2, word3));
        assertEquals(Set.of(word1, word2, word3), wordServiceImp.findAll());
        assertEquals(3, wordServiceImp.findAll().size());
        verify(wordRepository, times(2)).findAll();
    }

    @Test
    void findAllByWord() {
        when(wordRepository.findAllByWordValue(anyString())).thenReturn(Set.of(word2, word3));
        assertEquals(Set.of(word2, word3),wordServiceImp.findAllByWord("%methode%"));
        assertEquals(2, wordServiceImp.findAllByWord("%methode%").size());
    }

    @Test
    void save() {
        Word word=Word.builder().wordValue("saved word").build();
        when(wordRepository.save(any())).thenReturn(word);
        Word savedWord=wordServiceImp.save(word);
        assertEquals(word, savedWord);
    }

    @Test
    void delete() {
        wordServiceImp.delete(word2);
        verify(wordRepository).delete(any());
    }

    @Test
    void deleteById() {
        wordServiceImp.deleteById(word1.getUuid());
        verify(wordRepository).deleteById(any());
    }

    @Test
    void addSynonym() {
        Word newWord= Word.builder()
                .wordValue("4")
                .synonyms(new HashSet<>())
                .build();

        word1.getSynonyms().add(word2);
        word1.getSynonyms().add(word3);
        word2.getSynonyms().add(word1);
        word2.getSynonyms().add(word3);
        word3.getSynonyms().add(word1);
        word3.getSynonyms().add(word2);

        wordServiceImp.addSynonym(word1,newWord);

        assertEquals(Set.of(word2, word3, newWord), word1.getSynonyms());
        assertEquals(Set.of(word1, word3, newWord), word2.getSynonyms());
        assertEquals(Set.of(word1, word2, newWord), word3.getSynonyms());
        assertEquals(Set.of(word1, word2, word3), newWord.getSynonyms());
    }

    @Test
    void addAntonym() {
        Word newWord= Word.builder()
                .wordValue("4")
                .antonyms(new HashSet<>())
                .build();

        word1.getAntonyms().add(word2);
        word1.getAntonyms().add(word3);
        word2.getAntonyms().add(word1);
        word2.getAntonyms().add(word3);
        word3.getAntonyms().add(word1);
        word3.getAntonyms().add(word2);

        wordServiceImp.addAntonym(word1,newWord);

        assertEquals(Set.of(word2, word3, newWord), word1.getAntonyms());
        assertEquals(Set.of(word1, word3, newWord), word2.getAntonyms());
        assertEquals(Set.of(word1, word2, newWord), word3.getAntonyms());
        assertEquals(Set.of(word1, word2, word3), newWord.getAntonyms());
    }
}