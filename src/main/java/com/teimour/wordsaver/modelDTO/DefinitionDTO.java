package com.teimour.wordsaver.modelDTO;

import com.teimour.wordsaver.domain.WordClass;
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

    private WordClass wordClass;
    private String definitionValue;
    private Set<ExampleDTO> examples;
}
