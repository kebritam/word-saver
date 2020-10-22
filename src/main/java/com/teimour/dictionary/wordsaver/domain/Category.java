package com.teimour.dictionary.wordsaver.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    private final UUID id=UUID.randomUUID();

    private String categoryName;

    @ManyToMany
    @JoinTable(name = "word_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Set<Word> words;
}
