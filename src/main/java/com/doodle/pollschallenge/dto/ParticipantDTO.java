package com.doodle.pollschallenge.dto;

import lombok.Data;

@Data
public class ParticipantDTO {

    private Long id;
    private String name;
    private int[] preferences;
}
