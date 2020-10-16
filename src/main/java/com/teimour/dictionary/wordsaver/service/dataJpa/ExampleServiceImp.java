package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Example;
import com.teimour.dictionary.wordsaver.repository.ExampleRepository;
import com.teimour.dictionary.wordsaver.service.ExampleService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Service
public class ExampleServiceImp implements ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleServiceImp(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Override
    public Example findById(UUID uuid) {
        return null;
    }

    @Override
    public Set<Example> findAll() {
        return null;
    }

    @Override
    public Example save(Example object) {
        return null;
    }

    @Override
    public void delete(Example object) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }
}
