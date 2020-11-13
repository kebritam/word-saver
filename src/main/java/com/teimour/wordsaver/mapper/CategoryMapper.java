package com.teimour.wordsaver.mapper;

import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.modelDTO.CategoryDTO;
import com.teimour.wordsaver.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    default String wordToString(Word word){
        return (word==null) ? null : word.getWordValue();
    }

    default String categoryToString(Category category){
        return (category==null) ? null : category.getCategoryName();
    }
}
