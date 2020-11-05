package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.CategoryDTO;
import com.teimour.dictionary.wordsaver.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface CategoryMapper {

    @Mapping(source = "id", target = "uuid" )
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(target = "categoryName", source = "categoryName")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
