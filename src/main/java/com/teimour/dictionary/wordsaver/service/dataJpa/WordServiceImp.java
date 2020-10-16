package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.repository.WordRepository;
import com.teimour.dictionary.wordsaver.service.WordService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Service
public class WordServiceImp implements WordService {

    private final WordRepository wordRepository;

    public WordServiceImp(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word findById(UUID uuid) {
        return null;
    }

    @Override
    public Set<Word> findAll() {
        return null;
    }

    @Override
    public Word save(Word object) {
        return null;
    }

    @Override
    public void delete(Word object) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }
}
