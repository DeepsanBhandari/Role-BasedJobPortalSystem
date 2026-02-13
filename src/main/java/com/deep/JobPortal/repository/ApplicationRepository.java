package com.deep.JobPortal.repository;

import com.deep.JobPortal.model.Application;
import com.deep.JobPortal.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Find all applications by job seeker
    List<Application> findByApplicantId(Long userId);

    // Find all applications for a specific job
    List<Application> findByJobId(Long jobId);

    // Find all applications for a specific job by a specific recruiter
    @Query("SELECT a FROM Application a WHERE a.job.id = :jobId AND a.job.recruiter.id = :recruiterId")
    List<Application> findByJobIdAndRecruiter(@Param("jobId") Long jobId, @Param("recruiterId") Long recruiterId);

    // Check if user already applied for a job
    @Query("SELECT a FROM Application a WHERE a.applicant.id = :userId AND a.job.id = :jobId")
    Optional<Application> findByApplicantIdAndJobId(@Param("userId") Long userId, @Param("jobId") Long jobId);

    // Find applications by status
    List<Application> findByStatus(ApplicationStatus status);

    // Find all applications for recruiters jobs
    @Query("SELECT a FROM Application a WHERE a.job.recruiter.id = :recruiterId")
    List<Application> findByRecruiter(@Param("recruiterId") Long recruiterId);

    // Count applications for a job
    long countByJobId(Long jobId);

    // Count applications by job seeker
    long countByApplicantId(Long userId);
}
