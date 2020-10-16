package com.teimour.dictionary.wordsaver.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 14/10/2020
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Definition {

    @Id
    private final UUID uuid=UUID.randomUUID();

    @Enumerated(value = EnumType.STRING)
    private WordClasses wordClass;

    private String definitionValue;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "definition_id")
    private Set<Example> examples;
}
