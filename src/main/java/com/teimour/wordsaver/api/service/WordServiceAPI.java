package com.teimour.wordsaver.api.service;

import com.teimour.wordsaver.mapper.WordMapper;
import com.teimour.wordsaver.modelDTO.WordDTO;
import com.teimour.wordsaver.domain.Word;
import com.teimour.wordsaver.exception.NotFoundException;
import com.teimour.wordsaver.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@Service
public class WordServiceAPI implements WordServiceDTO {

    private final WordRepository wordRepository;

    public WordServiceAPI(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public WordDTO findByWord(String word) {
        Optional<Word> optionalWord=wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()){
            throw new NotFoundException("word not found");
        }
        return WordMapper.INSTANCE.wordToWordDTO(optionalWord.get());
    }

    @Override
    public Set<WordDTO> findAll() {
        Set<WordDTO> words=new HashSet<>();
        wordRepository.findAll().forEach(word -> words.add(WordMapper.INSTANCE.wordToWordDTO(word)));
        return words;
    }

    @Override
    public void delete(String word) {
        Optional<Word> optionalWord=wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()){
            throw new NotFoundException("word not found");
        }
        wordRepository.delete(optionalWord.get());
    }

}
