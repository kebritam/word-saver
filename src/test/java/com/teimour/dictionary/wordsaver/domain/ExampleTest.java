package com.teimour.dictionary.wordsaver.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kebritam
 * @project word-saver
 * @date 14/10/2020
 */

class ExampleTest {

    private Example example;

    @BeforeEach
    void setUp() {
        example=new Example();
    }

    @Test
    void generateUUID() {
        System.out.println(example.getUuid());
        Example newExample=new Example();
        System.out.println(newExample.getUuid());
        assertNotEquals(example.getUuid(),newExample.getUuid());
    }
}