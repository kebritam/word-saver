package com.teimour.wordsaver.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CategoryListDTO {

    @JsonProperty("categories")
    private Set<CategoryDTO> categoryDTOSet;
}
