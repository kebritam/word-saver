package com.teimour.wordsaver.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 20/10/2020
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String categoryName;

    @ManyToMany
    @JoinTable(name = "word_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Set<@Valid Word> words;
}
