package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.DefinitionDTO;
import com.teimour.dictionary.wordsaver.domain.Definition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface DefinitionMapper {

    DefinitionMapper INSTANCE= Mappers.getMapper(DefinitionMapper.class);

    @Mapping(source = "uuid", target = "id")
    DefinitionDTO definitionToDefinitionDTO(Definition definition);

    @Mapping(source = "definitionValue", target = "definitionValue")
    Definition definitionDTOToDefinition(DefinitionDTO definitionDTO);
}
