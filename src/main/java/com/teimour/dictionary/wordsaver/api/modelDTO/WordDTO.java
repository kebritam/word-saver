package com.teimour.dictionary.wordsaver.api.modelDTO;

import com.teimour.dictionary.wordsaver.domain.WordClasses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Getter
@Setter
@AllArgsConstructor
public class WordDTO {

    private String wordValue;
    private Set<WordClasses> wordClasses;
    private Set<DefinitionDTO> definitions;
    private NoteDTO notes;
    private String phonetic;
    private Set<WordDTO> synonyms;
    private Set<WordDTO> antonyms;
}
