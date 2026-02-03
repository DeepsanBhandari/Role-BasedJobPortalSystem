package com.deep.JobPortal.service;

import com.deep.JobPortal.dto.CreateJobRequest;
import com.deep.JobPortal.dto.JobResponse;
import com.deep.JobPortal.dto.UpdateJobRequest;
import com.deep.JobPortal.model.Job;
import com.deep.JobPortal.model.User;
import com.deep.JobPortal.repository.JobRepository;
import com.deep.JobPortal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Transactional
    public JobResponse createJob(CreateJobRequest request, String recruiterEmail){
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(()->new RuntimeException("Recruiter not found"));

        validateSalaryRange(request.getMinSalary(),request.getMaxSalary());

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setRequirements(request.getRequirements());
        job.setRecruiter(recruiter);
        job.setStatus("ACTIVE");

        // Save to database
        Job savedJob = jobRepository.save(job);

        // Convert to DTO and return
        return convertToJobResponse(savedJob);
    }

    // Get all active jobs (paginated)
    public Page<JobResponse> getAllActiveJobs(Pageable pageable) {
        Page<Job> jobPage = jobRepository.findByStatus("ACTIVE", pageable);
        return jobPage.map(this::convertToJobResponse);
    }

    // Get job by ID
    public JobResponse getJobById(Long jobId) {
        Job job = jobRepository.findByIdWithRecruiter(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return convertToJobResponse(job);
    }

    // Update job
    @Transactional
    public JobResponse updateJob(Long jobId, UpdateJobRequest request, String recruiterEmail) {
        // Load job with recruiter
        Job job = jobRepository.findByIdWithRecruiter(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Verify ownership
        if (!job.getRecruiter().getEmail().equals(recruiterEmail)) {
            throw new RuntimeException("You can only update your own job postings");
        }

        // Validate salary
        BigDecimal newMinSalary = request.getMinSalary() != null ? request.getMinSalary() : job.getMinSalary();
        BigDecimal newMaxSalary = request.getMaxSalary() != null ? request.getMaxSalary() : job.getMaxSalary();
        validateSalaryRange(newMinSalary, newMaxSalary);

        // Update fields
        if (request.getTitle() != null) {
            job.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            job.setDescription(request.getDescription());
        }
        if (request.getLocation() != null) {
            job.setLocation(request.getLocation());
        }
        if (request.getJobType() != null) {
            job.setJobType(request.getJobType());
        }
        if (request.getMinSalary() != null) {
            job.setMinSalary(request.getMinSalary());
        }
        if (request.getMaxSalary() != null) {
            job.setMaxSalary(request.getMaxSalary());
        }
        if (request.getRequirements() != null) {
            job.setRequirements(request.getRequirements());
        }
        if (request.getStatus() != null) {
            job.setStatus(request.getStatus());
        }

        // Save and return
        Job updatedJob = jobRepository.save(job);
        return convertToJobResponse(updatedJob);
    }

    // Delete job
    @Transactional
    public void deleteJob(Long jobId, String recruiterEmail) {
        // Load job
        Job job = jobRepository.findByIdWithRecruiter(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Verify ownership
        if (!job.getRecruiter().getEmail().equals(recruiterEmail)) {
            throw new RuntimeException("You can only delete your own job postings");
        }

        // Delete
        jobRepository.delete(job);
    }

    // Get recruiter's own jobs
    public List<JobResponse> getMyJobs(String recruiterEmail) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        List<Job> jobs = jobRepository.findByRecruiterId(recruiter.getId());
        return jobs.stream()
                .map(this::convertToJobResponse)
                .collect(Collectors.toList());
    }

    // Search jobs
    public Page<JobResponse> searchJobs(String keyword, Pageable pageable) {
        Page<Job> jobPage = jobRepository.searchJobs(keyword, pageable);
        return jobPage.map(this::convertToJobResponse);
    }

    // Business rule: Validate salary range
    private void validateSalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
        if (minSalary != null && maxSalary != null) {
            if (minSalary.compareTo(maxSalary) > 0) {
                throw new RuntimeException("Minimum salary cannot be greater than maximum salary");
            }
        }
    }

    // Convert entity to DTO
    private JobResponse convertToJobResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCompany(),
                job.getLocation(),
                job.getJobType(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getStatus(),
                job.getRequirements(),
                job.getRecruiter().getEmail(),
                job.getRecruiter().getFullName(),
                job.getCreatedAt(),
                job.getUpdatedAt()
        );
    }

    public Page<JobResponse> getJobs(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return JobRepository
                .findAllByActiveTrue(pageable)
                .map(this::mapToJobResponse);
    }
    private JobResponse mapToJobResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .location(job.getLocation())
                .createdAt(job.getCreatedAt())
                .build();
    }
}
