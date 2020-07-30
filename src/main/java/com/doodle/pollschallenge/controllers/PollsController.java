package com.doodle.pollschallenge.controllers;

import com.doodle.pollschallenge.dto.PollDTO;
import com.doodle.pollschallenge.exceptions.NoPollsForDateException;
import com.doodle.pollschallenge.exceptions.NoPollsForTitleException;
import com.doodle.pollschallenge.exceptions.NoPollsForUserException;
import com.doodle.pollschallenge.services.PollServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/polls")
public class PollsController {

    private PollServiceImpl pollService;

    @Autowired
    public PollsController(PollServiceImpl pollService) {
        this.pollService = pollService;
    }

    @GetMapping(value = "/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all polls for given title. ", response = PollDTO.class)
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<PollDTO>> searchByTitle(@PathVariable String title) {
        List<PollDTO> pollsList = pollService.findPollsByTitle(title);
        if(pollsList.isEmpty()) throw new NoPollsForTitleException(title);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pollsList);
        }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all polls for given user. ", response = PollDTO.class)
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<PollDTO>> searchByUser(@PathVariable String userId) {
        List<PollDTO> pollsList = pollService.findPollsForUser(userId);
        if(pollsList.isEmpty()) throw new NoPollsForUserException(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pollsList);
    }

    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all polls initiated after given date. ", response = PollDTO.class)
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<PollDTO>> searchByDate(@RequestParam("q") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate) {
        List<PollDTO> pollsList = pollService.findPollsAfterDate(fromDate);
        if(pollsList.isEmpty()) throw new NoPollsForDateException(fromDate.toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pollsList);
    }
}
