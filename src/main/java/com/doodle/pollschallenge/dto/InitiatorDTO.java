package com.doodle.pollschallenge.dto;

import lombok.Data;

@Data
public class InitiatorDTO {

    private String name;
    private String email;
    private boolean notify;
    private String timeZone;
}
