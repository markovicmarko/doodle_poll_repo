package com.doodle.pollschallenge.controllers;

import com.doodle.pollschallenge.SampleApplication;
import com.doodle.pollschallenge.dto.PollDTO;
import com.doodle.pollschallenge.exceptions.CustomRestExceptionHandler;
import com.doodle.pollschallenge.services.PollServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SampleApplication.class)
@AutoConfigureMockMvc
public class PollsControllerTest {

    private PollsController pollsController;

    @MockBean
    private PollServiceImpl pollService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pollsController = new PollsController(pollService);
        mockMvc = MockMvcBuilders.standaloneSetup(pollsController)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
    }

    @Test
    public void testGetPollsByTitle() throws Exception{
        String title = "Badminton";
        given(pollService.findPollsByTitle(title))
                .willReturn(createPollDtoWithTitle(title));

        mockMvc.perform(get("/polls/title/"+title)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].title").value("Badminton"));
        reset(pollService);
    }

    @Test
    public void testGetPollsForUser() throws Exception{
        String userId = "r44d7piq";
        given(pollService.findPollsForUser(userId))
                .willReturn(createPollDtoWithAdminKey(userId));

        mockMvc.perform(get("/polls/user/"+userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].adminKey").value("r44d7piq"));
        reset(pollService);
    }

    @Test
    public void testGetPollsAfterDate() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("1980-04-16");
        given(pollService.findPollsAfterDate(any(Date.class)))
                .willReturn(createPollDtoWithInitiated(date));

        mockMvc.perform(get("/polls/date")
                .param("q", dateFormat.format(date))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].initiated").value(324684000000L));
        reset(pollService);
    }

    @Test
    public void testGetNoPollsForTitle() throws Exception{
        String title = "Really bad title";
        given(pollService.findPollsByTitle(title))
                .willReturn(new ArrayList<PollDTO>());

        mockMvc.perform(get("/polls/title/"+title)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        reset(pollService);
    }

    @Test
    public void testGetNoPollsForUser() throws Exception{
        String userId = "Really bad user";
        given(pollService.findPollsForUser(userId))
                .willReturn(new ArrayList<PollDTO>());

        mockMvc.perform(get("/polls/user/"+userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        reset(pollService);
    }

    @Test
    public void testGetNoPollsForDate() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("1666-06-06");
        given(pollService.findPollsAfterDate(any(Date.class)))
                .willReturn(new ArrayList<PollDTO>());

        mockMvc.perform(get("/polls/date")
                .param("q", dateFormat.format(date))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        reset(pollService);
    }



    //
    private List<PollDTO> createPollDtoWithTitle(String title) {
        PollDTO pollDTO = new PollDTO();
        pollDTO.setTitle(title);
        List<PollDTO> pollDTOList = new ArrayList<>();
        pollDTOList.add(pollDTO);

        return pollDTOList;
    }

    private List<PollDTO> createPollDtoWithAdminKey(String adminKey) {
        PollDTO pollDTO = new PollDTO();
        pollDTO.setAdminKey(adminKey);
        List<PollDTO> pollDTOList = new ArrayList<>();
        pollDTOList.add(pollDTO);

        return pollDTOList;
    }

    private List<PollDTO> createPollDtoWithInitiated(Date date){
        PollDTO pollDTO = new PollDTO();
        pollDTO.setInitiated(date.getTime());
        List<PollDTO> pollDTOList = new ArrayList<>();
        pollDTOList.add(pollDTO);

        return pollDTOList;
    }
}
