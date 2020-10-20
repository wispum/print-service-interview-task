package org.home.printservice.converter;

import org.home.printservice.model.Job;
import org.home.printservice.model.request.JobRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class JobRequestConverter implements Converter<JobRequest, Job> {
    @Override
    public Job convert(JobRequest jobRequest) {
        Job job = new Job();
        job.setAmount(jobRequest.getAmount());
        job.setCreatedAt(OffsetDateTime.now());
        job.setDevice(jobRequest.getDevice());
        job.setType(Job.Type.valueOf(jobRequest.getType().name()));
        job.setUserId(jobRequest.getUser());
        job.setJobId(jobRequest.getId());
        return job;
    }
}
