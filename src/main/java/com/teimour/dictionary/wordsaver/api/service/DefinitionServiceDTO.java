package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.api.modelDTO.DefinitionDTO;

import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

public interface DefinitionServiceDTO {

    Set<DefinitionDTO> findAll(String word);

    DefinitionDTO create(String word, DefinitionDTO definitionDTO);

    DefinitionDTO update(String word, DefinitionDTO definitionDTO);

    void delete(String wordValue, DefinitionDTO definitionDTO);
}
