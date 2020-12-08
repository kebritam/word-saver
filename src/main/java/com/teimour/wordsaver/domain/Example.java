package com.teimour.wordsaver.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * @author kebritam
 * Project word-saver
 * Created on 14/10/2020
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Example {

    @Id
    @GeneratedValue
    @Column(name = "example_id")
    private  UUID uuid;

    @NotBlank
    private String exampleValue;

    @Override
    public String toString() {
        return exampleValue;
    }
}
