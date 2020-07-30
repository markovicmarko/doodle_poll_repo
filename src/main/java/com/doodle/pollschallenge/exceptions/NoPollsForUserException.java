package com.doodle.pollschallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoPollsForUserException extends RuntimeException {

    private String id;

    public NoPollsForUserException(String id) {
        super(String.format("No polls for user: '%s' found", id));
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
