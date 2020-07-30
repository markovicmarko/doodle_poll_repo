package com.doodle.pollschallenge.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.List;

@Data
@Document(collection = "poll")
public class Poll {

    @Id
    private String id;
    @TextIndexed
    private String adminKey;
    private long latestChange;
    @Indexed
    private long initiated;
    private int participantsCount;
    private int inviteesCount;
    @TextIndexed
    private String type;
    private int rowConstraint;
    private int columnConstraint;
    private boolean timeZone;
    private boolean hidden;
    @TextIndexed
    private String preferencesType;
    @TextIndexed
    private String state;
    @TextIndexed
    private String locale;
    @TextIndexed
    private String title;
    @TextIndexed
    private String description;
    private Location location;
    private Initiator initiator;
    private List<Option> options;
    @TextIndexed
    private String optionsHash;
    private List<Participant> participants;
    @TextIndexed
    private String [] invitees;
    private boolean multiDay;
    private boolean dateText;
    @TextIndexed
    private String device;
    @TextIndexed
    private String levels;

    @TextScore
    private Float textScore;
}
