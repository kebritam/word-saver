package com.teimour.dictionary.wordsaver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 14/10/2020
 */

@Getter
@Setter
@Entity
public class Note {

    @Id
    private final UUID uuid=UUID.randomUUID();

    private String notesValue;
}
