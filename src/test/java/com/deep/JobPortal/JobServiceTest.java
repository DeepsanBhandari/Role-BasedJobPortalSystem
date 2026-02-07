package com.deep.JobPortal;

import com.deep.JobPortal.dto.CreateJobRequest;
import com.deep.JobPortal.dto.JobResponse;
import com.deep.JobPortal.model.Job;
import com.deep.JobPortal.model.Role;
import com.deep.JobPortal.model.User;
import com.deep.JobPortal.repository.JobRepository;
import com.deep.JobPortal.repository.UserRepository;
import com.deep.JobPortal.service.JobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;



import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    void shouldThrowExceptionWhenJobSeekerTriesToCreateJob() {
        User jobSeeker = new User();
        jobSeeker.setEmail("test@gmail.com");
        jobSeeker.setRole(Role.JOB_SEEKER);

        CreateJobRequest request = new CreateJobRequest();
        request.setTitle("Backend Developer");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(jobSeeker));

        assertThrows(AccessDeniedException.class, () ->
                jobService.createJob(request, "test@gmail.com")
        );
    }

    @Test
    void shouldCreateJobWhenRecruiterCreatesJob() {
        // Arrange
        User recruiter = new User();
        recruiter.setId(1L);
        recruiter.setEmail("recruiter@gmail.com");
        recruiter.setRole(Role.RECRUITER);

        CreateJobRequest request = new CreateJobRequest();
        request.setTitle("Backend Developer");
        request.setCompany("DeepTech");
        request.setLocation("Remote");

        Job savedJob = new Job();
        savedJob.setId(10L);
        savedJob.setTitle("Backend Developer");
        savedJob.setCompany("DeepTech");
        savedJob.setLocation("Remote");
        savedJob.setRecruiter(recruiter);
        savedJob.setStatus("ACTIVE");

        when(userRepository.findByEmail("recruiter@gmail.com"))
                .thenReturn(Optional.of(recruiter));

        when(jobRepository.save(any(Job.class)))
                .thenReturn(savedJob);

        // Act
        JobResponse response =
                jobService.createJob(request, "recruiter@gmail.com");

        // Assert
        assertNotNull(response);
        assertEquals("Backend Developer", response.getTitle());
        assertEquals("DeepTech", response.getCompany());
    }

}

