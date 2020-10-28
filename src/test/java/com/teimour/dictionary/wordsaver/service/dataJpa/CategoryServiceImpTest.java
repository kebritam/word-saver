package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Category;
import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.exception.NotFoundException;
import com.teimour.dictionary.wordsaver.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
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
 * @date 27/10/2020
 */

@Slf4j
@ExtendWith(MockitoExtension.class)
class CategoryServiceImpTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImp categoryServiceImp;

    Category category1;
    Category category2;

    @BeforeEach
    void setUp() {
        category1=Category.builder().categoryName("sample").words(Set.of(
                Word.builder().wordValue("simple").build(),
                Word.builder().wordValue("sumple").build(),
                Word.builder().wordValue("somple").build(),
                Word.builder().wordValue("soumple").build()
        )).build();

        category2=Category.builder().categoryName("example").words(Set.of(
                Word.builder().wordValue("exumple").build(),
                Word.builder().wordValue("exomple").build(),
                Word.builder().wordValue("eximple").build(),
                Word.builder().wordValue("exoumple").build()
        )).build();
    }

    @Test
    void findByIdTrue() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category1));
        assertEquals(category1.getId(), categoryServiceImp.findById(any()).getId());
        assertEquals(category1, categoryServiceImp.findById(any()));
    }

    @Test()
    void findByIdFalse(){
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        NotFoundException exception= assertThrows(NotFoundException.class,
                ()->categoryServiceImp.findById(any()));

        assertEquals(exception.getMessage(), "category not found");
        verify(categoryRepository).findById(any());
    }

    @Test
    void findAll() {
        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));
        assertEquals(2, categoryServiceImp.findAll().size());
        assertEquals(Set.of(category1, category2), categoryServiceImp.findAll());
        assertNotEquals(Set.of(category1), categoryServiceImp.findAll());
        verify(categoryRepository, times(3)).findAll();
    }

    @Test
    void findByNameTrue() {
        when(categoryRepository.findByCategoryName(anyString())).thenReturn(Optional.of(category2));
        assertEquals(category2, categoryServiceImp.findByName(anyString()));
        when(categoryRepository.findByCategoryName(anyString())).thenReturn(Optional.of(category1));
        assertEquals(category1, categoryServiceImp.findByName(anyString()));
    }

    @Test
    void findByNameFalse() {
        when(categoryRepository.findByCategoryName(anyString())).thenReturn(Optional.empty());
        NotFoundException exception= assertThrows(NotFoundException.class,
                ()-> categoryServiceImp.findByName(anyString()));

        assertEquals(exception.getMessage(), "category not found");
        verify(categoryRepository).findByCategoryName(anyString());
    }

    @Test
    void save() {
        Category category=Category.builder().categoryName("forInstance").build();
        when(categoryRepository.save(any())).thenReturn(category);
        Category savedCategory= categoryServiceImp.save(category);
        verify(categoryRepository).save(any());
        assertEquals(category.getId(),savedCategory.getId());
        assertEquals("forInstance",category.getCategoryName());
    }

    @Test
    void updateBySave() {
        when(categoryRepository.save(any())).thenReturn(category1);
        Category savedCategory=categoryServiceImp.save(any());
        savedCategory.setCategoryName("something else");
        assertEquals(category1.getId(), savedCategory.getId());
        assertEquals(category1, savedCategory);
    }

    @Test
    void delete() {
        categoryServiceImp.delete(category1);
        verify(categoryRepository).delete(any());
    }

    @Test
    void deleteById() {
        categoryServiceImp.deleteById(category2.getId());
        verify(categoryRepository).deleteById(any());
    }
}