package com.teimour.wordsaver.mapper;

import com.teimour.wordsaver.modelDTO.ExampleDTO;
import com.teimour.wordsaver.domain.Example;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Mapper
public interface ExampleMapper {

    ExampleMapper INSTANCE= Mappers.getMapper(ExampleMapper.class);

    ExampleDTO exampleToExampleDTO(Example example);

    Example exampleDTOToExample(ExampleDTO exampleDTO);
}
