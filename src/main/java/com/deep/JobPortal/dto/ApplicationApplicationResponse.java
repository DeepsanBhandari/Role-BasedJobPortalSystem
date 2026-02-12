package com.deep.JobPortal.dto;

import com.deep.JobPortal.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationApplicationResponse {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String company;
    private String location;
    private ApplicationStatus status;
    private String remark;
    private LocalDateTime appliedDate;
    private LocalDateTime updatedDate;
}
