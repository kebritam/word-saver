package com.teimour.wordsaver.modelDTO;

import com.teimour.wordsaver.domain.WordClasses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Getter
@Setter
@AllArgsConstructor
public class DefinitionDTO {

    private WordClasses wordClass;
    private String definitionValue;
    private Set<ExampleDTO> examples;
}
