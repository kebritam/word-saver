package com.teimour.dictionary.wordsaver.controller;

import com.teimour.dictionary.wordsaver.service.NoteService;
import org.springframework.stereotype.Controller;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
}
