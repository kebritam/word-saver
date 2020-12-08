package com.teimour.wordsaver.domain;

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
 * Project word-saver
 * Created on 14/10/2020
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Definition {

    @Id
    @GeneratedValue
    @Column(name = "definition_id")
    private UUID uuid;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private WordClass wordClass;

    @NotBlank
    private String definitionValue;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "definition_id")
    private Set<@Valid Example> examples;
}
