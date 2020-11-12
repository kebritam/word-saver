package com.teimour.wordsaver.repository;

import com.teimour.wordsaver.domain.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 15/10/2020
 */

public interface DefinitionRepository extends JpaRepository<Definition, UUID> {
}
