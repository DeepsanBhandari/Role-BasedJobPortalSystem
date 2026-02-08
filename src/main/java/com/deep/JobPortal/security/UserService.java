package com.deep.JobPortal.security;

import com.deep.JobPortal.dto.JobResponse;
import com.deep.JobPortal.dto.UpdateProfileRequest;
import com.deep.JobPortal.model.Job;
import com.deep.JobPortal.model.User;
import com.deep.JobPortal.repository.JobRepository;
import com.deep.JobPortal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // by email
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    public User updateProfile(String email, UpdateProfileRequest request){
        User user = getUserByEmail(email);


        if(request.getFullName()!= null){
            user.setFullName(request.getFullName());
        }
        if(request.getPhone()!=null){
            user.setPhone(request.getPhone());
        }
        if(request.getBio()!=null){
            user.setBio(request.getBio());
        }
        return userRepository.save(user);
    }


    public void deleteUser(Long userId){
        User user = getUserById(userId);
        userRepository.delete(user);
    }




}
