package com.teimour.wordsaver.service.dataJpa;

import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.exception.NotFoundException;
import com.teimour.wordsaver.repository.WordRepository;
import com.teimour.wordsaver.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 16/10/2020
 */

@Slf4j
@Service
public class WordServiceImp implements WordService {

    private final WordRepository wordRepository;

    public WordServiceImp(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word findById(UUID uuid) {
        Optional<Word> word=wordRepository.findById(uuid);
        if (word.isPresent()){
            return word.get();
        } else {
            throw new NotFoundException("word not found");
        }
    }

    @Override
    public Word findByWord(String word) {
        Optional<Word> wordOptional=wordRepository.findByWordValueIgnoreCase(word);
        if (wordOptional.isPresent()){
            return wordOptional.get();
        } else {
            throw new NotFoundException("word not found");
        }
    }

    @Override
    public Set<Word> findAll() {
        return Set.copyOf(wordRepository.findAll());
    }

    @Override
    public Set<Word> findAllByWord(String word) {
        return wordRepository.findAllByWordValue(word);
    }

    @Override
    public Word save(Word object) {
        return wordRepository.save(object);
    }

    @Override
    public void delete(Word object) {
        wordRepository.delete(object);
        log.info("word deleted");
    }

    @Override
    public void deleteById(UUID uuid) {
        wordRepository.deleteById(uuid);
        log.info("word deleted");
    }

    @Override
    public void addSynonym(Word processedWord, Word newWord) {

        processedWord.getSynonyms().forEach(word -> {
            newWord.getSynonyms().add(word);
            word.getSynonyms().add(newWord);
        });

        processedWord.getSynonyms().add(newWord);
        newWord.getSynonyms().add(processedWord);
    }

    @Override
    public void addAntonym(Word processedWord, Word newWord) {

        processedWord.getSynonyms().forEach(word -> {
            newWord.getAntonyms().add(word);
            word.getAntonyms().add(newWord);
        });

        processedWord.getAntonyms().add(newWord);
        newWord.getAntonyms().add(processedWord);
    }


}
