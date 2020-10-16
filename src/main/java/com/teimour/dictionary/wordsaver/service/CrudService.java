package com.teimour.dictionary.wordsaver.service;

import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 15/10/2020
 */

public interface CrudService<T, ID> {

    T findById(ID id);

    Set<T> findAll();

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
