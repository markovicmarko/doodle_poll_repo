package com.doodle.pollschallenge.controllers;

import com.doodle.pollschallenge.exceptions.HandlerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatchAllController {

    @RequestMapping(value = "/**")
    public ResponseEntity<Object> noHandlerMappingFound() {
        throw new HandlerNotFoundException("No handler mapping found.");
    }
}
