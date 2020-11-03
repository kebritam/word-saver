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

    @NotBlank
    private String categoryName;

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "word_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Set<@Valid Word> words;

    @Override
    public String toString() {
        return categoryName;
    }
}
