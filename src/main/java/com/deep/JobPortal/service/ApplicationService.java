package com.deep.JobPortal.service;

import com.deep.JobPortal.dto.ApplicationApplicationResponse;
import com.deep.JobPortal.dto.ApplicationResponse;
import com.deep.JobPortal.dto.UpdateApplicationStatusRequest;
import com.deep.JobPortal.model.*;
import com.deep.JobPortal.repository.ApplicationRepository;
import com.deep.JobPortal.repository.JobRepository;
import com.deep.JobPortal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Job seeker applies for a job
     */
    public ApplicationResponse applyForJob(Long userId, Long jobId) {
        User applicant = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        // Check if user already applied
        if (applicationRepository.findByApplicantIdAndJobId(userId, jobId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already applied for this job");
        }

        Application application = new Application();
        application.setApplicant(applicant);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);

        Application savedApplication = applicationRepository.save(application);
        return convertToApplicationResponse(savedApplication);
    }

    /**
     * Get all applications for a job seeker
     */
    public List<ApplicationApplicationResponse> getMyApplications(Long userId) {
        return applicationRepository.findByApplicantId(userId)
                .stream()
                .map(this::convertToJobSeekerResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all applicants for a recruiter's jobs
     */
    public List<ApplicationResponse> getApplicantsForRecruiter(Long recruiterId) {
        return applicationRepository.findByRecruiter(recruiterId)
                .stream()
                .map(this::convertToApplicationResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get applicants for a specific job (recruiter only)
     */
    public List<ApplicationResponse> getApplicantsForJob(Long jobId, Long recruiterId) {
        // Verify the recruiter owns this job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        if (!job.getRecruiter().getId().equals(recruiterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to view applicants for this job");
        }

        return applicationRepository.findByJobIdAndRecruiter(jobId, recruiterId)
                .stream()
                .map(this::convertToApplicationResponse)
                .collect(Collectors.toList());
    }

    /**
     * Update application status (recruiter only)
     */
    public ApplicationResponse updateApplicationStatus(Long applicationId, Long recruiterId, UpdateApplicationStatusRequest request) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        // Verify the recruiter owns this application's job
        if (!application.getJob().getRecruiter().getId().equals(recruiterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to update this application");
        }

        application.setStatus(request.getStatus());
        if (request.getRemark() != null) {
            application.setRemark(request.getRemark());
        }

        Application updatedApplication = applicationRepository.save(application);
        return convertToApplicationResponse(updatedApplication);
    }

    /**
     * Get all applications (admin only)
     */
    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::convertToApplicationResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get applications by status (admin only)
     */
    public List<ApplicationResponse> getApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatus(status)
                .stream()
                .map(this::convertToApplicationResponse)
                .collect(Collectors.toList());
    }

    /**
     * Withdraw application (job seeker only)
     */
    public void withdrawApplication(Long applicationId, Long userId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        if (!application.getApplicant().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only withdraw your own applications");
        }

        applicationRepository.delete(application);
    }

    /**
     * Convert Application entity to ApplicationResponse DTO
     */
    private ApplicationResponse convertToApplicationResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setApplicantId(application.getApplicant().getId());
        response.setApplicantName(application.getApplicant().getFullName());
        response.setApplicantEmail(application.getApplicant().getEmail());
        response.setJobId(application.getJob().getId());
        response.setJobTitle(application.getJob().getTitle());
        response.setCompany(application.getJob().getCompany());
        response.setStatus(application.getStatus());
        response.setRemark(application.getRemark());
        response.setAppliedDate(application.getCreatedAt());
        response.setUpdatedDate(application.getUpdatedAt());
        return response;
    }

    /**
     * Convert Application entity to ApplicationApplicationResponse DTO (for job seeker view)
     */
    private ApplicationApplicationResponse convertToJobSeekerResponse(Application application) {
        ApplicationApplicationResponse response = new ApplicationApplicationResponse();
        response.setId(application.getId());
        response.setJobId(application.getJob().getId());
        response.setJobTitle(application.getJob().getTitle());
        response.setCompany(application.getJob().getCompany());
        response.setLocation(application.getJob().getLocation());
        response.setStatus(application.getStatus());
        response.setRemark(application.getRemark());
        response.setAppliedDate(application.getCreatedAt());
        response.setUpdatedDate(application.getUpdatedAt());
        return response;
    }
}
