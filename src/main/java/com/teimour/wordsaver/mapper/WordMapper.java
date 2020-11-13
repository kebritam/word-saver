package com.teimour.wordsaver.mapper;

import com.teimour.wordsaver.domain.Category;
import com.teimour.wordsaver.modelDTO.WordDTO;
import com.teimour.wordsaver.domain.Word;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Mapper
public interface WordMapper {

    WordMapper INSTANCE= Mappers.getMapper(WordMapper.class);

    WordDTO wordToWordDTO(Word word);

    default String wordToString(Word word){
        return (word == null) ? null : word.getWordValue();
    }

    default String categoryToString(Category category){
        return (category==null) ? null : category.getCategoryName();
    }
}
