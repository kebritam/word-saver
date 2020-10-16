package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Example;
import com.teimour.dictionary.wordsaver.repository.ExampleRepository;
import com.teimour.dictionary.wordsaver.service.ExampleService;
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
public class ExampleServiceImp implements ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleServiceImp(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Override
    public Example findById(UUID uuid) {
        Optional<Example> example=exampleRepository.findById(uuid);
        if (example.isPresent()){
            return example.get();
        } else {
            throw new NullPointerException();// TODO: 10/16/2020 implement better exception
        }
    }

    @Override
    public Set<Example> findAll() {
        return Set.copyOf(exampleRepository.findAll());
    }

    @Override
    public Example save(Example object) {
        return exampleRepository.save(object);
    }

    @Override
    public void delete(Example object) {
        exampleRepository.delete(object);
        log.info("example deleted");
    }

    @Override
    public void deleteById(UUID uuid) {
        exampleRepository.deleteById(uuid);
        log.info("example deleted");
    }
}
