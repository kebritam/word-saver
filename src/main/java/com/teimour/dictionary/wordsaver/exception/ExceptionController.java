package com.teimour.dictionary.wordsaver.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author kebritam
 * @project word-saver
 * @date 25/10/2020
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public String notFound(Exception exception, Model model){
        model.addAttribute("exception", exception);
        return "404Error";
    }
}
