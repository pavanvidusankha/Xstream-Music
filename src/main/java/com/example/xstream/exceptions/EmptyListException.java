package com.example.xstream.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT,reason = "there is no items to return")
public class EmptyListException extends RuntimeException{

    public EmptyListException(String type) {

        super(String.format("No "+type+"to return"));
    }
}
