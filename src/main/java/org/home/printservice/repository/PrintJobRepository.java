package org.home.printservice.repository;

import org.home.printservice.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PrintJobRepository extends MongoRepository<Job, String>, PrintFilterJobRepository {
}
