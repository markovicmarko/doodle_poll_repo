package com.doodle.pollschallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HandlerNotFoundException extends RuntimeException {
    private String id;

    public HandlerNotFoundException(String id) {
        super(String.format("%s", id));
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
