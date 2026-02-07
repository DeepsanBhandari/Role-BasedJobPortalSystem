package com.deep.JobPortal.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateJobRequest {
    private String title;
    private String description;
    private String company;
    private String location;
    private String jobType;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String requirements;
}
