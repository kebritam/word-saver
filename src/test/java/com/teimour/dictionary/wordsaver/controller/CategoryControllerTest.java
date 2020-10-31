package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author kebritam
 * @project word-saver
 * @date 29/10/2020
 */

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    Word word1;
    Word word2;
    Word word3;
    Word word4;

    Category category1;
    Category category2;

    final String CATEGORY_ONE_NAME="shy";
    final String CATEGORY_TWO_NAME="not shy";

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(categoryController).build();

        word1=Word.builder().wordValue("1").build();
        word2=Word.builder().wordValue("2").build();
        word3=Word.builder().wordValue("3").build();
        word4=Word.builder().wordValue("4").build();

        category1=Category.builder().categoryName(CATEGORY_ONE_NAME).words(Set.of(word1,word2,word3)).build();
        category2=Category.builder().categoryName(CATEGORY_TWO_NAME).words(Set.of(word3,word4)).build();
    }

    @Test
    void showCategory() throws Exception{
        when(categoryService.findByName(anyString())).thenReturn(category1);
        mockMvc.perform(get("/category/{name}/show", CATEGORY_ONE_NAME))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("category", "words"))
                .andExpect(model().attribute("words", category1.getWords()))
                .andExpect(view().name("category"));

        verify(categoryService).findByName(anyString());
    }

    @Test
    void editCategory() throws Exception {
        when(categoryService.findByName(anyString())).thenReturn(category2);
        mockMvc.perform(get("/category/{name}/edit", CATEGORY_TWO_NAME))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", categoryService.findByName(anyString())))
                .andExpect(view().name("categoryForm"));

        verify(categoryService, times(2)).findByName(anyString());
    }

    @Test
    void submitEditCategory() throws Exception {
        when(categoryService.findByName(anyString())).thenReturn(category2);

        mockMvc.perform(post("/category/{name}/edit", CATEGORY_TWO_NAME))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        verify(categoryService).findByName(anyString());
        verify(categoryService).save(any());
    }

    @Test
    void newCategory() throws Exception {
        mockMvc.perform(get("/category/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("categoryForm"));
    }

    @Test
    void submitNewCategory() throws Exception {
        mockMvc.perform(post("/category/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(categoryService).save(any());
    }
}