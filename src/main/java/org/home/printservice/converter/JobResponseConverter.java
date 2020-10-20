package org.home.printservice.converter;

import org.home.printservice.model.Job;
import org.home.printservice.model.response.JobResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class JobResponseConverter implements Converter<Job, JobResponse> {
    @Override
    public JobResponse convert(Job job) {
        JobResponse jobResponse = new JobResponse();
        jobResponse.setAmount(Integer.valueOf(job.getAmount()).longValue());
        jobResponse.setDevice(job.getDevice());
        jobResponse.setJobId(job.getJobId());
        jobResponse.setTime(job.getCreatedAt());
        jobResponse.setType(JobResponse.Type.valueOf(job.getType().name()));
        return jobResponse;
    }
}
