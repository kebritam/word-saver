package com.teimour.dictionary.wordsaver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 14/10/2020
 */

@Getter
@Setter
@Entity
public class Definition {

    @Id
    private final UUID uuid=UUID.randomUUID();

    @Enumerated(value = EnumType.STRING)
    private WordClasses wordClass;

    private String definitionValue;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "definiton_id")
    private Set<Example> examples;
}
