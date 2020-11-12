package com.teimour.wordsaver.service.dataJpa;

import com.teimour.wordsaver.domain.Definition;
import com.teimour.wordsaver.exception.NotFoundException;
import com.teimour.wordsaver.repository.DefinitionRepository;
import com.teimour.wordsaver.service.DefinitionService;
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
public class DefinitionServiceImp implements DefinitionService {

    private final DefinitionRepository definitionRepository;

    public DefinitionServiceImp(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    @Override
    public Definition findById(UUID uuid) {
        Optional<Definition> definition=definitionRepository.findById(uuid);
        if (definition.isPresent())
            return definition.get();
        else
            throw new NotFoundException("definition not found");
    }

    @Override
    public Set<Definition> findAll() {
        return Set.copyOf(definitionRepository.findAll());
    }

    @Override
    public Definition save(Definition object) {
        return definitionRepository.save(object);
    }

    @Override
    public void delete(Definition object) {
        definitionRepository.delete(object);
        log.info("definition deleted.");
    }

    @Override
    public void deleteById(UUID uuid) {
        definitionRepository.deleteById(uuid);
        log.info("definition deleted.");
    }
}
