package com.teimour.wordsaver.controller;

import com.teimour.wordsaver.domain.Category;
import com.teimour.wordsaver.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author kebritam
 * Project word-saver
 * Created on 28/10/2020
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

        Category category1= Category.builder().categoryName("one").build();

        when(categoryService.findAll()).thenReturn(Set.of(category1));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attribute("categoryList", Matchers.hasSize(1)))
                .andExpect(model().attribute("categoryList", Matchers.hasItem(
                        allOf(
                                hasProperty("id"),
                                hasProperty("categoryName",is("one")),
                                hasProperty("words")
                        )))
                )
                .andExpect(view().name("index"));

        verify(categoryService).findAll();
    }
}