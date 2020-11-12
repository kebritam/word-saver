package com.teimour.wordsaver.modelDTO;

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
public class CategoryDTO {

    private String categoryName;
    private Set<WordDTO> words;
}
