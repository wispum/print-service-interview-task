package org.home.printservice.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JobRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String user;
    @NotBlank
    private String device;
    private int amount;
    @NotBlank
    private Type type;

    public enum Type {
        PRINT, COPY, SCAN, FAX;
    }
}
