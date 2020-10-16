package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Note;
import com.teimour.dictionary.wordsaver.repository.NoteRepository;
import com.teimour.dictionary.wordsaver.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Slf4j
@Service
public class NoteServiceImp implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImp(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note findById(UUID uuid) {
        Optional<Note> note=noteRepository.findById(uuid);
        if (note.isPresent()){
            return note.get();
        } else {
            throw new NullPointerException();// TODO: 10/16/2020 implement better exception
        }
    }

    @Override
    public Set<Note> findAll() {
        return Set.copyOf(noteRepository.findAll());
    }

    @Override
    public Note save(Note object) {
        return noteRepository.save(object);
    }

    @Override
    public void delete(Note object) {
        noteRepository.delete(object);
        log.info("note deleted");
    }

    @Override
    public void deleteById(UUID uuid) {
        noteRepository.deleteById(uuid);
        log.info("note deleted");
    }
}
