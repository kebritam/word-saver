package com.teimour.wordsaver.exception;

import com.teimour.wordsaver.resources.View;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author kebritam
 * Project word-saver
 * Created on 25/10/2020
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public String notFound(Exception exception, Model model){
        model.addAttribute("exception", exception);

        return View.NOT_FOUND;
    }
}
