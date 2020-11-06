package com.teimour.dictionary.wordsaver.api.service;

import com.teimour.dictionary.wordsaver.api.mapper.DefinitionMapper;
import com.teimour.dictionary.wordsaver.api.mapper.ExampleMapper;
import com.teimour.dictionary.wordsaver.api.modelDTO.DefinitionDTO;
import com.teimour.dictionary.wordsaver.domain.Definition;
import com.teimour.dictionary.wordsaver.domain.Example;
import com.teimour.dictionary.wordsaver.domain.Word;
import com.teimour.dictionary.wordsaver.exception.NotFoundException;
import com.teimour.dictionary.wordsaver.repository.DefinitionRepository;
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
public class DefinitionServiceAPI implements DefinitionServiceDTO {

    private final DefinitionRepository definitionRepository;
    private final WordRepository wordRepository;

    public DefinitionServiceAPI(DefinitionRepository definitionRepository, WordRepository wordRepository) {
        this.definitionRepository = definitionRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public Set<DefinitionDTO> findAll(String wordValue) {
        Optional<Word> wordOptional=wordRepository.findByWordValueIgnoreCase(wordValue);
        if (wordOptional.isEmpty()){
            throw new NotFoundException("word not found");
        }
        Set<DefinitionDTO> returnSet=new HashSet<>();
        wordOptional.get().getDefinitions().forEach(
                definition -> returnSet.add(DefinitionMapper.INSTANCE.definitionToDefinitionDTO(definition))
        );
        return returnSet;
    }

    @Override
    public DefinitionDTO create(String wordValue, DefinitionDTO definitionDTO) {
        Optional<Word> wordOptional=wordRepository.findByWordValueIgnoreCase(wordValue);
        if (wordOptional.isEmpty()){
            throw new NotFoundException("word not found");
        }
        Definition savedDefinition= DefinitionMapper.INSTANCE.definitionDTOToDefinition(definitionDTO);
        savedDefinition= definitionRepository.save(savedDefinition);

        Word word=wordOptional.get();
        word.getDefinitions().add(savedDefinition);
        wordRepository.save(word);

        return DefinitionMapper.INSTANCE.definitionToDefinitionDTO(savedDefinition);
    }

    @Override
    public DefinitionDTO update(String wordValue, DefinitionDTO definitionDTO) {
        Optional<Word> wordOptional=wordRepository.findByWordValueIgnoreCase(wordValue);
        if (wordOptional.isEmpty()){
            throw new NotFoundException("word not found");
        }
        Definition savedDefinition= DefinitionMapper.INSTANCE.definitionDTOToDefinition(definitionDTO);
        savedDefinition.setWordClass(definitionDTO.getWordClass());
        savedDefinition.setDefinitionValue(definitionDTO.getDefinitionValue());

        Set<Example> exampleSet=new HashSet<>();
        definitionDTO.getExamples().forEach(
                definition -> exampleSet.add(ExampleMapper.INSTANCE.exampleDTOToExample(definition))
        );
        savedDefinition.setExamples(exampleSet);
        return DefinitionMapper.INSTANCE.definitionToDefinitionDTO(definitionRepository.save(savedDefinition));
    }

    @Override
    public void delete(String wordValue, DefinitionDTO definitionDTO) {
        Optional<Word> wordOptional=wordRepository.findByWordValueIgnoreCase(wordValue);
        if (wordOptional.isEmpty()){
            throw new NotFoundException("word not found");
        }

        Word word= wordOptional.get();
        Definition foundDefinition=word.getDefinitions().stream().filter(
                definition -> definitionDTO.getDefinitionValue().equals(definition.getDefinitionValue())
        ).findFirst().orElseThrow(NotFoundException::new);
        definitionRepository.delete(foundDefinition);
        wordRepository.save(word);
    }
}
