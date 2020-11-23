package com.teimour.wordsaver.domain;

import lombok.*;

import javax.persistence.Entity;
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
    private final UUID uuid=UUID.randomUUID();

    @NotNull
    private String notesValue;
}
