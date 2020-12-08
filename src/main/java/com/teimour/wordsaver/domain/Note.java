package com.teimour.wordsaver.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 14/10/2020
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Note {

    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private UUID uuid;

    @NotNull
    private String notesValue;
}
