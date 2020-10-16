package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Note;
import com.teimour.dictionary.wordsaver.repository.NoteRepository;
import com.teimour.dictionary.wordsaver.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Service
public class NoteServiceImp implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImp(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note findById(UUID uuid) {
        return null;
    }

    @Override
    public Set<Note> findAll() {
        return null;
    }

    @Override
    public Note save(Note object) {
        return null;
    }

    @Override
    public void delete(Note object) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }
}
