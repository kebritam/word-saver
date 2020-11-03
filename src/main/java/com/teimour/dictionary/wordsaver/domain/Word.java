package com.teimour.dictionary.wordsaver.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
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
public class Word {

    @Id
    private final UUID uuid=UUID.randomUUID();

    @NotBlank
    private String wordValue;

    @NotEmpty
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = WordClasses.class)
    @JoinTable(name = "word_class")
    private Set<@Valid WordClasses> wordClasses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id")
    private Set<@Valid Definition> definitions;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private Note notes;

    @NotBlank
    private String phonetic;

    @ManyToMany
    @JoinTable(name = "synonyms",
        joinColumns = @JoinColumn(name = "word_id"),
        inverseJoinColumns = @JoinColumn(name = "synonym_id"))
    private Set<@Valid Word> synonyms;

    @ManyToMany
    @JoinTable(name = "antonyms",
        joinColumns = @JoinColumn(name = "word_id"),
        inverseJoinColumns = @JoinColumn(name = "antonym_id"))
    private Set<@Valid Word> antonyms;

    @ManyToMany(mappedBy = "words")
    private Set<@Valid Category> categories;

    @Override
    public String toString() {
        return wordValue;
    }
}
