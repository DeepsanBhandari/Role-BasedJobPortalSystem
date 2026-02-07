package com.deep.JobPortal.repository;

import com.deep.JobPortal.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByRecruiterId(Long recruiterId);

    Page<Job> findByStatus(String status, Pageable pageable);

    @Query("SELECT j FROM Job j JOIN FETCH j.recruiter WHERE j.id = :id")
    Optional<Job> findByIdWithRecruiter(@Param("id") Long id);

    // Search jobs by title or company
    @Query("SELECT j FROM Job j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Job> searchJobs(@Param("keyword") String keyword, Pageable pageable);



}
