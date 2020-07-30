package com.doodle.pollschallenge.domain;

import lombok.Data;

@Data
public class Participant {

    private Long id;
    private String name;
    private int[] preferences;
}
