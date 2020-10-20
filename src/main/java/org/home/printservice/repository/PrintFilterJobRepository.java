package org.home.printservice.repository;

import org.home.printservice.model.Job;
import org.home.printservice.model.request.PrintFilterRequest;

import java.util.List;


public interface PrintFilterJobRepository {
    List<Job> findAllByRequestFilter(PrintFilterRequest request);
}
