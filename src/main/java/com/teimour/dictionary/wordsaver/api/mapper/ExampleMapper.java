package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.ExampleDTO;
import com.teimour.dictionary.wordsaver.domain.Example;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface ExampleMapper {

    @Mapping(source = "uuid", target = "uuid")
    ExampleDTO exampleToExampleDTO(Example example);
}
