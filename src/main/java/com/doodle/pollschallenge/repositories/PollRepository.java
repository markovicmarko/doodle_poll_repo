package com.doodle.pollschallenge.repositories;

import com.doodle.pollschallenge.domain.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PollRepository extends MongoRepository<Poll, String> {

    List<Poll> findByAdminKey(String userId);

    List<Poll> findByTitle(String title);

    @Query(value = "{'initiated' :{ $gte: ?0 } }")
    List<Poll> findByInitiated(long unixTimestamp);
}
