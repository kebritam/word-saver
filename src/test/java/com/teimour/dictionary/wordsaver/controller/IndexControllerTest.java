package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author kebritam
 * @project word-saver
 * @date 28/10/2020
 */

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    IndexController indexController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc= standaloneSetup(indexController).build();
    }

    @Test
    void getIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(matchAll(
                        status().isOk(),
                        model().size(1),
                        model().attributeExists("categoryList")
                ));

        verify(categoryService).findAll();
    }
}