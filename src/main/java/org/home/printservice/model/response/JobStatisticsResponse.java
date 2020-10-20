package org.home.printservice.model.response;

import java.util.ArrayList;
import java.util.Collection;

public class JobStatisticsResponse extends ArrayList<JobResponse> {
    public JobStatisticsResponse(Collection<? extends JobResponse> c) {
        super(c);
    }
}
