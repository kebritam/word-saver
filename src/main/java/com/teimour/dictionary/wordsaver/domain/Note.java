package com.teimour.dictionary.wordsaver.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 14/10/2020
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

    @NotBlank
    private String notesValue;

    @Override
    public String toString() {
        return notesValue;
    }
}
