package com.doodle.pollschallenge.domain;

import lombok.Data;

@Data
public class Option {

    private long start;
    private long end;
    private long startDate;
    private long endDate;
    private long startDateTime;
    private long endDateTime;
    private long dateTime;
    private long date;
    private boolean allday;
    private String text;
    private boolean available;
}
