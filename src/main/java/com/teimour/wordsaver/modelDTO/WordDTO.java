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
public class WordDTO {

    private String wordValue;
    private Set<WordClass> wordClasses;
    private Set<DefinitionDTO> definitions;
    private Set<String> categories;
    private NoteDTO notes;
    private String phonetic;
    private Set<String> synonyms;
    private Set<String> antonyms;
}
