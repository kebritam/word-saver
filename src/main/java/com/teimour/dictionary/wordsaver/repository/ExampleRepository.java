package com.teimour.dictionary.wordsaver.repository;

import com.teimour.dictionary.wordsaver.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 15/10/2020
 */

public interface ExampleRepository extends JpaRepository<Example, UUID> {
}
