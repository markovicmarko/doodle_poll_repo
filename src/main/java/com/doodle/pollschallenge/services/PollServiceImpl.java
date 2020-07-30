package com.doodle.pollschallenge.services;

import com.doodle.pollschallenge.dto.PollDTO;
import com.doodle.pollschallenge.mapper.DtoMapper;
import com.doodle.pollschallenge.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    private PollRepository pollRepository;
    private DtoMapper DtoMapper;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository, DtoMapper DtoMapper) {
        this.pollRepository = pollRepository;
        this.DtoMapper = DtoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PollDTO> findPollsForUser(String userId){
        return DtoMapper.mapList(pollRepository.findByAdminKey(userId), PollDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PollDTO> findPollsByTitle(String title){
        return DtoMapper.mapList(pollRepository.findByTitle(title), PollDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PollDTO> findPollsAfterDate(Date date){
        return DtoMapper.mapList(pollRepository.findByInitiated(date.getTime()), PollDTO.class);
    }
}
