package com.deep.JobPortal.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateJobRequest {
    private String title;
    private String description;
    private String location;
    private String jobType;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String requirements;
    private String status;
}
