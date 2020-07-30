package com.doodle.pollschallenge.services;

import com.doodle.pollschallenge.SampleApplication;
import com.doodle.pollschallenge.domain.Poll;
import com.doodle.pollschallenge.dto.PollDTO;
import com.doodle.pollschallenge.mapper.DtoMapper;
import com.doodle.pollschallenge.repositories.PollRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SampleApplication.class)
public class PollServiceTest {

    private PollService pollService;
    @Mock
    private PollRepository repository;
    private DtoMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mapper = new DtoMapper(new ModelMapper());
        pollService = new PollServiceImpl(repository, mapper);
    }

    @Test
    public void fetchPollsByTitleReturnPollList() {
        String title = "Badminton";
        when(repository.findByTitle(title)).thenReturn(getListOfPolls());
        List<PollDTO> polls = pollService.findPollsByTitle(title);
        Assertions.assertTrue(polls.size() == 2);
    }

    @Test
    public void fetchPollsByUserReturnPollList() {
        String userId = "mmac123";
        when(repository.findByAdminKey(userId)).thenReturn(getListOfPolls());
        List<PollDTO> polls = pollService.findPollsForUser(userId);
        Assertions.assertTrue(polls.size() == 2);
    }

    @Test
    public void fetchPollsByDateReturnPollList() {
        long date = 1234567890L;
        when(repository.findByInitiated(date)).thenReturn(getListOfPolls());
        List<PollDTO> polls = pollService.findPollsAfterDate(new Date(date));
        Assertions.assertTrue(polls.size() == 2);
    }

    @Test
    public void fetchPollsByTitleReturnEmptyCollection() {
        String title = "NoTitleAtAll";
        when(repository.findByTitle(title)).thenReturn(Collections.emptyList());
        List<PollDTO> polls = pollService.findPollsByTitle(title);
        assertThat(polls, empty());
    }

    private List<Poll> getListOfPolls() {
        Poll poll1 = new Poll();
        Poll poll2 = new Poll();
        List<Poll> pollList = new ArrayList<>();
        pollList.add(poll1);
        pollList.add(poll2);

        return pollList;
    }
}