package com.deep.JobPortal.controller;

import com.deep.JobPortal.dto.ApiResponse;
import com.deep.JobPortal.dto.ApplicationApplicationResponse;
import com.deep.JobPortal.dto.ApplicationResponse;
import com.deep.JobPortal.dto.UpdateApplicationStatusRequest;
import com.deep.JobPortal.model.ApplicationStatus;
import com.deep.JobPortal.model.User;
import com.deep.JobPortal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * Job seeker applies for a job
     */
    @PostMapping("/apply/{jobId}")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<ApiResponse<?>> applyForJob(@PathVariable Long jobId) {
        User currentUser = getCurrentUser();
        ApplicationResponse application = applicationService.applyForJob(currentUser.getId(), jobId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Successfully applied for the job", application));
    }

    /**
     * Job seeker views all their applications
     */
    @GetMapping("/my-applications")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<ApiResponse<?>> getMyApplications() {
        User currentUser = getCurrentUser();
        List<ApplicationApplicationResponse> applications = applicationService.getMyApplications(currentUser.getId());

        return ResponseEntity.ok(new ApiResponse<>(true, "Applications retrieved successfully", applications));
    }

    /**
     * Recruiter views applicants for their jobs
     */
    @GetMapping("/applicants")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse<?>> getApplicantsForRecruiter() {
        User currentUser = getCurrentUser();
        List<ApplicationResponse> applicants = applicationService.getApplicantsForRecruiter(currentUser.getId());

        return ResponseEntity.ok(new ApiResponse<>(true, "Applicants retrieved successfully", applicants));
    }

    /**
     * Recruiter views applicants for a specific job
     */
    @GetMapping("/applicants/job/{jobId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse<?>> getApplicantsForJob(@PathVariable Long jobId) {
        User currentUser = getCurrentUser();
        List<ApplicationResponse> applicants = applicationService.getApplicantsForJob(jobId, currentUser.getId());

        return ResponseEntity.ok(new ApiResponse<>(true, "Job applicants retrieved successfully", applicants));
    }

    /**
     * Recruiter updates application status
     */
    @PutMapping("/{applicationId}/status")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse<?>> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestBody UpdateApplicationStatusRequest request) {

        User currentUser = getCurrentUser();
        ApplicationResponse updatedApplication = applicationService.updateApplicationStatus(
                applicationId, currentUser.getId(), request);

        return ResponseEntity.ok(new ApiResponse<>(true, "Application status updated successfully", updatedApplication));
    }

    /**
     * Job seeker withdraws their application
     */
    @DeleteMapping("/{applicationId}")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<ApiResponse<?>> withdrawApplication(@PathVariable Long applicationId) {
        User currentUser = getCurrentUser();
        applicationService.withdrawApplication(applicationId, currentUser.getId());

        return ResponseEntity.ok(new ApiResponse<>(true, "Application withdrawn successfully", null));
    }

    /**
     * Admin views all applications
     */
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> getAllApplications() {
        List<ApplicationResponse> applications = applicationService.getAllApplications();

        return ResponseEntity.ok(new ApiResponse<>(true, "All applications retrieved successfully", applications));
    }

    /**
     * Admin views applications by status
     */
    @GetMapping("/admin/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByStatus(status);

        return ResponseEntity.ok(new ApiResponse<>(true, "Applications by status retrieved successfully", applications));
    }

    /**
     * Helper method to get current authenticated user
     */
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
