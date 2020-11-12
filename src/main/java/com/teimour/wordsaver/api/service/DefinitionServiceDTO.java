package com.teimour.wordsaver.api.service;

import com.teimour.wordsaver.modelDTO.DefinitionDTO;

import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

public interface DefinitionServiceDTO {

    Set<DefinitionDTO> findAll(String word);

    DefinitionDTO create(String word, DefinitionDTO definitionDTO);

    DefinitionDTO update(String word, DefinitionDTO definitionDTO);

    void delete(String wordValue, DefinitionDTO definitionDTO);
}
