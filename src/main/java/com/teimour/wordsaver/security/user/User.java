package com.teimour.wordsaver.security.user;

import lombok.Getter;
import lombok.Setter;

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
    private int id;
    private String username;
    private String password;
    private String email;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
