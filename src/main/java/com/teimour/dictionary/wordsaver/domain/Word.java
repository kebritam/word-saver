package com.teimour.dictionary.wordsaver.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    private String wordValue;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = WordClasses.class)
    @JoinTable(name = "word_class")
    private Set<WordClasses> wordClasses;

    @OneToMany
    @JoinColumn(name = "word_id")
    private Set<Definition> definitions;

    @OneToOne
    @JoinColumn(name = "note_id")
    private Note notes;

    private String phonetic; // TODO: 10/14/2020 improve phonetics

    @ElementCollection
    @JoinTable(name = "word_tag")
    private Set<String> tags;// TODO: 10/14/2020 improve tags

    // TODO: 10/15/2020 Test these bellow... maybe just one way association worked
    @ManyToMany
    @JoinTable(name = "synonyms",
    joinColumns = @JoinColumn(name = "word_id"),
    inverseJoinColumns = @JoinColumn(name = "synonym_id"))
    private Set<Word> synonyms;

    @ManyToMany
    @JoinTable(name = "synonyms",
    joinColumns = @JoinColumn(name = "synonym_id"),
    inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Set<Word> synonymOf;

    @ManyToMany
    @JoinTable(name = "antonyms",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "antonym_id"))
    private Set<Word> antonyms;

    @ManyToMany
    @JoinTable(name = "antonyms",
            joinColumns = @JoinColumn(name = "antonym_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Set<Word> antonymOf;
}
