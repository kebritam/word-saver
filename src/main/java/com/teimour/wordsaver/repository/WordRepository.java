package com.teimour.wordsaver.repository;

import com.teimour.wordsaver.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 15/10/2020
 */

public interface WordRepository extends JpaRepository<Word, UUID> {

    Optional<Word> findByWordValueIgnoreCase(String word);

    Set<Word> findAllByWordValue(String word);
}
