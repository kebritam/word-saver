package com.teimour.wordsaver.mapper;

import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.modelDTO.CategoryDTO;
import com.teimour.wordsaver.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "wordClasses", ignore = true)
    @Mapping(target = "synonyms", ignore = true)
    @Mapping(target = "phonetic", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "definitions", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "antonyms", ignore = true)
    @Mapping(source = "value", target = "wordValue")
    Word stringToWord(String value);

    default String wordToString(Word word){
        return (word==null) ? null : word.getWordValue();
    }
}
