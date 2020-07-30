package com.doodle.pollschallenge.services;

import com.doodle.pollschallenge.dto.PollDTO;

import java.util.Date;
import java.util.List;

public interface PollService {

    List<PollDTO> findPollsForUser(String userId);
    List<PollDTO> findPollsByTitle(String title);
    List<PollDTO> findPollsAfterDate(Date date);
}
