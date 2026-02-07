package com.deep.JobPortal.controller;

import com.deep.JobPortal.dto.ApiResponse;
import com.deep.JobPortal.dto.CreateJobRequest;
import com.deep.JobPortal.dto.JobResponse;
import com.deep.JobPortal.dto.UpdateJobRequest;
import com.deep.JobPortal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    //Create job (RECRUITER only)
    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @RequestBody CreateJobRequest request, @AuthenticationPrincipal UserDetails userDetails){
        JobResponse response= jobService.createJob(request,userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

   //Get all active jobs
   @GetMapping
    public ResponseEntity<Page<JobResponse>> getAllActiveJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue ="10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction)
    {

        Sort.Direction sortDirection= direction.equalsIgnoreCase("ASC")?Sort.Direction.ASC:Sort.Direction.DESC;
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortDirection,sortBy));
        Page<JobResponse> jobs=jobService.getAllActiveJobs(pageable);
        return ResponseEntity.ok(jobs);

      }

    //Get job by ID
    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long jobId){
        JobResponse response=jobService.getJobById(jobId);
        return ResponseEntity.ok(response);
    }

    //Update job(RECRUITER only, owner only)
    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long jobId,
                                                     @RequestBody UpdateJobRequest request,@AuthenticationPrincipal UserDetails userDetails){
        JobResponse response=jobService.updateJob(jobId,request,userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    //Delete job (RECRUITER ONLY, OWNER only)
    @PreAuthorize("hasRole('RECRUITER')")
        @DeleteMapping({"/{jobId}"})
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId,@AuthenticationPrincipal UserDetails userDetails){
        jobService.deleteJob(jobId, userDetails.getUsername());
        return ResponseEntity.ok("Job deleted");

    }
    // Get my jobs (RECRUITER only)
    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/my-jobs")
    public ResponseEntity<List<JobResponse>> getMyJobs(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<JobResponse> jobs = jobService.getMyJobs(userDetails.getUsername());
        return ResponseEntity.ok(jobs);
    }

    // Search jobs (any authenticated user)
    @GetMapping("/search")
    public ResponseEntity<Page<JobResponse>> searchJobs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<JobResponse> jobs = jobService.searchJobs(keyword,pageable);
        return ResponseEntity.ok(jobs);
    }

}
