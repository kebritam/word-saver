package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.repository.WordRepository;
import com.teimour.dictionary.wordsaver.service.WordService;
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
            throw new NullPointerException();// TODO: 10/16/2020 implement better exception
        }
    }

    @Override
    public Word findByWord(String word) {
        Optional<Word> wordOptional=wordRepository.findByWordValueIgnoreCase(word);
        if (wordOptional.isPresent()){
            return wordOptional.get();
        } else {
            throw new NullPointerException(); // TODO: 10/18/2020 implement better exception 
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
    public void addSynonym(Word word1, Word word2) {

        word1.getSynonyms().forEach(word -> word2.getSynonyms().add(word));

        word1.getSynonyms().add(word2);
        word2.getSynonyms().add(word1);
    }

    @Override
    public void addAntonym(Word word1, Word word2) {

        word1.getAntonyms().forEach(word -> word2.getAntonyms().add(word));

        word1.getAntonyms().add(word2);
        word2.getAntonyms().add(word1);
    }


}
