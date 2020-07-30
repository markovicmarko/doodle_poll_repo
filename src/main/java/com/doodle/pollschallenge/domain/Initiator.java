package com.doodle.pollschallenge.domain;

import lombok.Data;

@Data
public class Initiator {

    private String name;
    private String email;
    private boolean notify;
    private String timeZone;
}
