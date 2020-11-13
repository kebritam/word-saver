package com.teimour.wordsaver.mapper;

import com.teimour.wordsaver.modelDTO.WordDTO;
import com.teimour.wordsaver.domain.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(target = "categories", ignore = true)
    Word wordDTOToWord(WordDTO wordDTO);

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
        return (word == null) ? null : word.getWordValue();
    }
}
