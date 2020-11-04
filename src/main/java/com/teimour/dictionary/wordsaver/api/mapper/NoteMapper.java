package com.teimour.dictionary.wordsaver.api.mapper;

import com.teimour.dictionary.wordsaver.api.modelDTO.NoteDTO;
import com.teimour.dictionary.wordsaver.domain.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Mapper
public interface NoteMapper {

    @Mapping(source = "uuid", target = "uuid")
    NoteDTO noteToNoteDTO(Note note);
}
