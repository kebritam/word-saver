package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.domain.WordClasses;
import com.teimour.wordsaver.service.CategoryService;
import com.teimour.wordsaver.service.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author kebritam
 * Project word-saver
 * Created on 31/10/2020
 */

@ExtendWith(MockitoExtension.class)
class WordControllerTest {

    @Mock
    WordService wordService;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    WordController wordController;

    MockMvc mockMvc;

    Word word1;
    Word word2;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(wordController).build();

        word1= Word.builder().wordValue("word1").categories(new HashSet<>()).build();
        word2= Word.builder().wordValue("word2").build();
    }

    @Test
    void showWord() throws Exception {
        when(wordService.findByWord(anyString())).thenReturn(word1);
        mockMvc.perform(get("/word/{wordValue}/show", word1.getWordValue()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categoryList", "word"))
                .andExpect(model().attribute("categoryList", word1.getCategories()))
                .andExpect(model().attribute("word", word1))
                .andExpect(view().name("word"));

        verify(wordService).findByWord(anyString());
    }

    @Test
    void editWord() throws Exception {
        when(wordService.findByWord(anyString())).thenReturn(word1);

        mockMvc.perform(get("/word/{wordValue}/edit", word1.getWordValue()))
                .andExpect(status().isOk())
                .andExpect(model().size(4))
                .andExpect(model().attribute("word", word1))
                .andExpect(model().attribute("classes", WordClasses.values()))
                .andExpect(model().attribute("allCategories", word1.getCategories()))
                .andExpect(model().attribute("allWords", new HashSet<>()))
                .andExpect(view().name("wordForm"));

        verify(wordService).findByWord(anyString());
        verify(wordService).findAll();
        verify(categoryService).findAll();
    }

    @Test
    void submitEditWord() throws Exception {
        when(wordService.findByWord(anyString())).thenReturn(word1);
        mockMvc.perform(post("/word/{wordValue}/edit", word1.getWordValue()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/word/"+word1.getWordValue()+"/show"));

        verify(wordService).save(any(Word.class));
        verify(wordService).findByWord(anyString());
    }

    @Test
    void newWord() throws Exception {
        mockMvc.perform(get("/word/new"))
                .andExpect(status().isOk())
                .andExpect(model().size(4))
                .andExpect(model().attributeExists("word"))
                .andExpect(model().attribute("classes", WordClasses.values()))
                .andExpect(model().attribute("allCategories", new HashSet<>()))
                .andExpect(model().attribute("allWords", new HashSet<>()))
                .andExpect(view().name("wordForm"));
    }

    @Test
    void submitNewWord() throws Exception {
        mockMvc.perform(post("/word/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/word/*/show"));

        verify(wordService).save(any(Word.class));
    }
}