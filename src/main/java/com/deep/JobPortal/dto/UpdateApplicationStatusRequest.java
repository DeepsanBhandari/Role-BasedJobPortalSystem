package com.deep.JobPortal.dto;

import com.deep.JobPortal.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicationStatusRequest {
    private ApplicationStatus status;
    private String remark;
}
