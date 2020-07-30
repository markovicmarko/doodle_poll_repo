package com.doodle.pollschallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoPollsForTitleException extends RuntimeException{

    private String id;

    public NoPollsForTitleException(String id) {
        super(String.format("No polls for title: '%s' found", id));
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
