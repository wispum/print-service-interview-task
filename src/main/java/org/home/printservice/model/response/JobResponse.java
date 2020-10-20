package org.home.printservice.model.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class JobResponse {
    private String jobId;
    private String userId;
    private String device;
    private long amount;
    private Type type;
    private OffsetDateTime time;
    public enum Type {
        PRINT, COPY, SCAN, FAX;
    }
}
