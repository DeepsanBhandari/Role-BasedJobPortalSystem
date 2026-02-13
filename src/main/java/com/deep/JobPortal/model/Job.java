package com.deep.JobPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length=200)
    private String title;

    @Column(nullable = false,length=2000)
    private String description;

    @Column(nullable = false,length=2000)
    private String company;

    @Column(nullable = false,length=200)
    private String location;

    @Column(name="job_type", nullable = false, length = 50)
    private String jobType;

    @Column(name="min_salary")
    private BigDecimal minSalary;

    @Column(name = "max_salary")
    private BigDecimal maxSalary;

    @Column(nullable = false)
    private String status="ACTIVE";

    @Column(nullable = false)
    private String requirements;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="recruiter_id", nullable = false)
    private User recruiter;

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Application> applications = new ArrayList<>();
}

