package com.teimour.wordsaver.security.user;

import org.springframework.data.repository.CrudRepository;

/**
 * @author kebritam
 * Project word-saver
 * Created on 23/11/2020
 */

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
