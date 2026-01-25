package com.deep.JobPortal.dto;

import com.deep.JobPortal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor

public class AuthResponse {

    private String token;
    private String email;
    private String fullName;
    private Role role;
}
