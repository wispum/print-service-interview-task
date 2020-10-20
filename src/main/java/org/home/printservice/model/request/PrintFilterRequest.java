package org.home.printservice.model.request;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PrintFilterRequest {
    private String userId;
    private JobRequest.Type type;
    private String deviceId;
    private OffsetDateTime timeFrom;
    private OffsetDateTime timeTo;
}
