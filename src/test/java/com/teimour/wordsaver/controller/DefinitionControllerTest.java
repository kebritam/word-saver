package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.domain.WordClasses;
import com.teimour.wordsaver.service.DefinitionService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author kebritam
 * Project word-saver
 * Created on 31/10/2020
 */

@ExtendWith(MockitoExtension.class)
class DefinitionControllerTest {

    @Mock
    DefinitionService definitionService;

    @Mock
    WordService wordService;

    @InjectMocks
    DefinitionController definitionController;

    MockMvc mockMvc;

    Definition definition1;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(definitionController).build();

        definition1= Definition.builder()
                .wordClass(WordClasses.VERB)
                .definitionValue("one").build();

    }

    @Test
    void deleteDefinition() throws Exception {
        mockMvc.perform(
                get("/word/{wordValue}/definition/{uuid}/remove", "word one", definition1.getUuid())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/word/word one/show"));

        verify(definitionService).deleteById(any());
    }

    @Test
    void addDefinition() throws Exception {
        when(wordService.findByWord(anyString())).thenReturn(Word.builder().wordValue("word one").build());
        mockMvc.perform(get("/word/{wordValue}/definition/new", "word one"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("word", "definition", "classes"))
                .andExpect(model().attribute("word", wordService.findByWord(anyString())))
                .andExpect(model().attribute("classes", WordClasses.values()))
                .andExpect(view().name("definitionForm"));

        verify(wordService, times(2)).findByWord(anyString());
    }

    @Test
    void submitAddDefinition() throws Exception {
        when(wordService.findByWord(anyString()))
                .thenReturn(Word.builder().wordValue("word one").definitions(new HashSet<>()).build());
        mockMvc.perform(post("/word/{wordValue}/definition/new", "word one"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/word/word one/show"));

        verify(wordService).findByWord(any());
        verify(wordService).save(any());
    }

    @Test
    void editDefinition() throws Exception {
        when(wordService.findByWord(anyString())).thenReturn(Word.builder().wordValue("word one").build());
        when(definitionService.findById(any())).thenReturn(definition1);
        mockMvc.perform(
                get("/word/{wordValue}/definition/{uuid}/edit", "word one", definition1.getUuid())
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("word", "definition", "classes"))
                .andExpect(model().attribute("word", wordService.findByWord("word one")))
                .andExpect(model().attribute("definition", definitionService.findById(definition1.getUuid())))
                .andExpect(model().attribute("classes", WordClasses.values()))
                .andExpect(view().name("definitionForm"));

        verify(wordService, times(2)).findByWord(anyString());
        verify(definitionService, times(2)).findById(any());
    }

    @Test
    void submitEditDefinition() throws Exception {
        when(wordService.findByWord(anyString()))
                .thenReturn(Word.builder().wordValue("word one").definitions(new HashSet<>()).build());
        when(definitionService.findById(any()))
                .thenReturn(definition1);
        mockMvc.perform(
                post("/word/{wordValue}/definition/{uuid}/edit", "word one", definition1.getUuid())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/word/word one/show"));

        verify(wordService).save(any(Word.class));
        verify(wordService).findByWord(anyString());
        verify(definitionService).findById(any());
    }
}