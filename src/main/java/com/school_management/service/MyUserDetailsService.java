package com.school_management.service;

import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return this.repo.findByEmailId(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
