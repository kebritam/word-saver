package com.teimour.wordsaver.mapper;

import com.teimour.wordsaver.modelDTO.DefinitionDTO;
import com.teimour.wordsaver.domain.Definition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Mapper
public interface DefinitionMapper {

    DefinitionMapper INSTANCE= Mappers.getMapper(DefinitionMapper.class);

    DefinitionDTO definitionToDefinitionDTO(Definition definition);

    Definition definitionDTOToDefinition(DefinitionDTO definitionDTO);
}
