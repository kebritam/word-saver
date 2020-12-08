package com.teimour.wordsaver.security.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    @Column(name = "user_id")
    private int id;

    private LocalDate signupDate=LocalDate.now();

    @NotBlank
    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(unique = true, nullable = false, length = 60)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
