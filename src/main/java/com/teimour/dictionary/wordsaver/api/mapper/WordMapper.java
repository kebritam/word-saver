package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.WordDTO;
import com.teimour.dictionary.wordsaver.domain.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface WordMapper {

    WordMapper INSTANCE= Mappers.getMapper(WordMapper.class);

    WordDTO wordToWordDTO(Word word);

    @Mapping(target = "categories", ignore = true)
    Word wordDTOToWord(WordDTO wordDTO);
}
