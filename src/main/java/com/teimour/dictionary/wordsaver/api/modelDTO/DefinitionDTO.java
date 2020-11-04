package com.teimour.dictionary.wordsaver.api.modelDTO;

import com.teimour.dictionary.wordsaver.domain.WordClasses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Getter
@Setter
@AllArgsConstructor
public class DefinitionDTO {

    private UUID id;
    private WordClasses wordClass;
    private String definitionValue;
    private Set<ExampleDTO> examples;
}
