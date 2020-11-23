package com.teimour.wordsaver.security.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.util.List;

/**
 * @author kebritam
 * Project word-saver
 * Created on 23/11/2020
 */

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
