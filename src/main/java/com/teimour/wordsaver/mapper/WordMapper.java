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
}
