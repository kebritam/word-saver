package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.DefinitionDTO;
import com.teimour.dictionary.wordsaver.domain.Definition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface DefinitionMapper {

    @Mapping(source = "uuid", target = "id")
    DefinitionDTO definitionToDefinitionDTO(Definition definition);

    @Mapping(source = "definitionValue", target = "definitionValue")
    Definition definitionDTOToDefinition(DefinitionDTO definitionDTO);
}
