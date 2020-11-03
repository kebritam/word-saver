package com.teimour.dictionary.wordsaver.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private WordClasses wordClass;

    @NotBlank
    private String definitionValue;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "definition_id")
    private Set<@Valid Example> examples;
}
