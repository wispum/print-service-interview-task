package org.home.printservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@Document
@CompoundIndexes({
        @CompoundIndex(name = "jobId_device", def = "{'jobId' : 1, 'device': 1}")
})
public class Job {
    private String jobId;
    private String userId;
    private String device;
    private int amount;
    private Type type;
    private OffsetDateTime createdAt;

    public enum Type {
        PRINT, COPY, SCAN, FAX;
    }
}
