package com.varnaa.abm.repository;

import com.varnaa.abm.model.Bug;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface BugRepository extends MongoRepository<Bug, Integer> {
}
