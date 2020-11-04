package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.WordDTO;
import com.teimour.dictionary.wordsaver.domain.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface WordMapper {

    @Mapping(source = "uuid", target = "uuid")
    WordDTO wordToWordDTO(Word word);
}
