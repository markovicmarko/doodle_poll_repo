package com.doodle.pollschallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollDTO implements Serializable {

    private String id;
    private String adminKey;
    private long latestChange;
    private long initiated;
    private int participantsCount;
    private int inviteesCount;
    private String type;
    private int rowConstraint;
    private int columnConstraint;
    private boolean timeZone;
    private boolean hidden;
    private String preferencesType;
    private String state;
    private String locale;
    private String title;
    private String description;
    private LocationDTO location;
    private InitiatorDTO initiator;
    private List<OptionDTO> options;
    private String optionsHash;
    private List<ParticipantDTO> participants;
    private String [] invitees;
    private boolean multiDay;
    private boolean dateText;
    private String device;
    private String levels;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public void setInitiated(long initiated) {
        this.initiated = initiated;
    }
}
