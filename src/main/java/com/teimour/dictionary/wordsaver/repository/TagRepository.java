package com.teimour.dictionary.wordsaver.repository;

import com.teimour.dictionary.wordsaver.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
