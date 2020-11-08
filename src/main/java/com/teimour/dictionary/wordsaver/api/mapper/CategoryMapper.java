package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.CategoryDTO;
import com.teimour.dictionary.wordsaver.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
