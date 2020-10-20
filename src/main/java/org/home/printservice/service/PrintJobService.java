package org.home.printservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.home.printservice.model.Job;
import org.home.printservice.model.request.JobRequest;
import org.home.printservice.model.request.PrintFilterRequest;
import org.home.printservice.model.request.XmlDataJob;
import org.home.printservice.model.response.JobResponse;
import org.home.printservice.model.response.JobStatisticsResponse;
import org.home.printservice.repository.PrintJobRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrintJobService {
    private final PrintJobRepository repository;
    private final Converter<JobRequest, Job> jobRequestConverter;
    private final Converter<Job, JobResponse> jobResponseConverter;


    public Map<String, Long> saveJobRequest(XmlDataJob xmlDataJob) {
        Map<String, Long> savedMap = new HashMap<>(xmlDataJob.size());
        for (JobRequest jobRequest : xmlDataJob) {
            try {
                Job savedJob = repository.save(jobRequestConverter.convert(jobRequest));
                if (savedMap.containsKey(jobRequest.getUser())){
                    savedMap.put(jobRequest.getUser(), savedMap.get(jobRequest.getUser()) + jobRequest.getAmount());
                }else {
                    savedMap.put(jobRequest.getUser(), Integer.valueOf(jobRequest.getAmount()).longValue());
                }
            } catch (DuplicateKeyException e) {
                log.error("Already has entry with device id {} and job id {}", jobRequest.getDevice(), jobRequest.getId());
            }
        }
        return savedMap;
    }


    public JobStatisticsResponse getStatistics(PrintFilterRequest filter) {
        List<JobResponse> foundRecords = repository.findAllByRequestFilter(filter).stream().map(jobResponseConverter::convert).collect(Collectors.toList());
        return new JobStatisticsResponse(foundRecords);
    }
}
