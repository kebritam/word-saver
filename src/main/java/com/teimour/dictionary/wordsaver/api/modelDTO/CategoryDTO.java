package com.teimour.dictionary.wordsaver.api.modelDTO;

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
public class CategoryDTO {

    private UUID uuid;
    private String categoryName;
    private Set<WordDTO> words;
}
