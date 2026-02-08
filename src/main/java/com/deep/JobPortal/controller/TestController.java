package com.deep.JobPortal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    //Any authenticated user can access
    @GetMapping("/public")
    public String publicEndpoint(@AuthenticationPrincipal UserDetails user){
        return "Hello "  +user.getUsername()+"! Ths is a public endpoint.";
    }

    //Only JOB_SEEKERS can access
    @PreAuthorize("hasRole('JOB_SEEKER')")
    @GetMapping("/job-seeker")
    public String jobSeekerEndpoint(@AuthenticationPrincipal UserDetails user){
        return "Welcome Job Seeker!" +user.getUsername();
    }

    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/recruiter")
    public String recruiterEndpoint(@AuthenticationPrincipal UserDetails user){
        return "Welcome Recruiter:" +user.getUsername();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint(@AuthenticationPrincipal UserDetails user){
        return "Welcome ADMIN:" +user.getUsername();
    }

    //Hiring team
    @PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
    @GetMapping("/hiring-team")
    public String hiringTeamEndpoint(@AuthenticationPrincipal UserDetails user){
        return "Welcome Hiring-Team:" +user.getUsername();
    }
}
