package com.deep.JobPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String company;
    private String location;
    private String jobType;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String status;
    private String requirements;
    private String recruiterEmail;
    private String recruiterName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
