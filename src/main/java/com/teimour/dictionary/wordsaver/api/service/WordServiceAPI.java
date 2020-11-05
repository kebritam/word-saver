package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.api.mapper.WordMapper;
import com.teimour.dictionary.wordsaver.api.modelDTO.WordDTO;
import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.exception.NotFoundException;
import com.teimour.dictionary.wordsaver.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 04/11/2020
 */

@Service
public class WordServiceAPI implements WordServiceDTO {

    private final WordRepository wordRepository;
    private final WordMapper wordMapper;

    public WordServiceAPI(WordRepository wordRepository, WordMapper wordMapper) {
        this.wordRepository = wordRepository;
        this.wordMapper = wordMapper;
    }

    @Override
    public WordDTO findByWord(String word) {
        Optional<Word> optionalWord=wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()){
            throw new NotFoundException("word not found");
        }
        return wordMapper.wordToWordDTO(optionalWord.get());
    }

    @Override
    public Set<WordDTO> findAll() {
        Set<WordDTO> words=new HashSet<>();
        wordRepository.findAll().forEach(word -> words.add(wordMapper.wordToWordDTO(word)));
        return words;
    }

    @Override
    public WordDTO create(WordDTO wordDTO) {
        Word mappedWord=wordMapper.wordDTOToWord(wordDTO);
        wordRepository.save(mappedWord);
        return wordMapper.wordToWordDTO(mappedWord);
    }

    @Override
    public WordDTO update(String word, WordDTO wordDTO) {
        Optional<Word> optionalWord=wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()){
            throw new NotFoundException("word not found");
        }
        Word mappedWord=wordMapper.wordDTOToWord(wordDTO);
        Word returnWord=optionalWord.get();

        returnWord.setDefinitions(mappedWord.getDefinitions());
        returnWord.setSynonyms(mappedWord.getSynonyms());
        returnWord.setAntonyms(mappedWord.getAntonyms());
        returnWord.setWordClasses(mappedWord.getWordClasses());
        returnWord.setPhonetic(mappedWord.getPhonetic());
        returnWord.setNotes(mappedWord.getNotes());
        returnWord.setWordValue(mappedWord.getWordValue());
        returnWord.setCategories(mappedWord.getCategories());

        return wordMapper.wordToWordDTO(wordRepository.save(returnWord));
    }

    @Override
    public void delete(String word, WordDTO wordDTO) {
        Optional<Word> optionalWord=wordRepository.findByWordValueIgnoreCase(word);
        if (optionalWord.isEmpty()){
            throw new NotFoundException("word not found");
        }
        wordRepository.delete(optionalWord.get());
    }

}
