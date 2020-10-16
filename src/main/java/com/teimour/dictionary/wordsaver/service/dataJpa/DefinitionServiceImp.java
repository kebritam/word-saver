package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Definition;
import com.teimour.dictionary.wordsaver.repository.DefinitionRepository;
import com.teimour.dictionary.wordsaver.service.DefinitionService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Service
public class DefinitionServiceImp implements DefinitionService {

    private final DefinitionRepository definitionRepository;

    public DefinitionServiceImp(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    @Override
    public Definition findById(UUID uuid) {
        return null;
    }

    @Override
    public Set<Definition> findAll() {
        return null;
    }

    @Override
    public Definition save(Definition object) {
        return null;
    }

    @Override
    public void delete(Definition object) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }
}
