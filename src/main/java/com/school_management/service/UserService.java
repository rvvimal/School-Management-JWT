package com.school_management.service;


import com.school_management.dto.SignInRequestDTO;
import com.school_management.dto.SignUpRequestDTO;
import com.school_management.entity.User;
import com.school_management.enums.Role;
import com.school_management.exception.BadRequestServiceAlertException;
import com.school_management.exception.ResourceNotFoundException;
import com.school_management.exception.UnAuthorizedException;
import com.school_management.repository.UserRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public User adminCreate(final SignUpRequestDTO signUpRequestDTO) {
        final User user = new User();
        if (userRepository.existsByEmailId(user.getEmailId())) {
            throw new BadRequestServiceAlertException("Already emailId exists");
        }
        user.setName(signUpRequestDTO.getUserName());
        user.setEmailId(signUpRequestDTO.getEmailId());
        user.setPassword(encoder.encode(signUpRequestDTO.getPassword()));
        user.setRole(Role.ADMIN);
        return this.userRepository.save(user);
    }

    @Transactional
    public User teacherCreate(final SignUpRequestDTO signUpRequestDTO) {
        final User user = new User();
        if (userRepository.existsByEmailId(user.getEmailId())) {
            throw new BadRequestServiceAlertException("Already emailId exists");
        }
        user.setName(signUpRequestDTO.getUserName());
        user.setEmailId(signUpRequestDTO.getEmailId());
        user.setPassword(encoder.encode(signUpRequestDTO.getPassword()));
        user.setRole(Role.TEACHER);
        return this.userRepository.save(user);
    }

    @Transactional
    public User studentCreate(final SignUpRequestDTO signUpRequestDTO) {
        final User user = new User();
        if (userRepository.existsByEmailId(user.getEmailId())) {
            throw new BadRequestServiceAlertException("Already emailId exists");
        }
        user.setName(signUpRequestDTO.getUserName());
        user.setEmailId(signUpRequestDTO.getEmailId());
        user.setPassword(encoder.encode(signUpRequestDTO.getPassword()));
        user.setRole(Role.STUDENT);
        return this.userRepository.save(user);
    }

    @Transactional
    public Map<String, String> signIn(final SignInRequestDTO signInRequestDTO) {
        final User user = this.userRepository.findByEmailId(signInRequestDTO.getEmailId())
                .orElseThrow(() -> new AuthorizationDeniedException("Incorrect EmailId"));
        if (!encoder.matches(signInRequestDTO.getPassword(), user.getPassword())) {
            throw new AuthorizationDeniedException("Incorrect Password");
        }
        authManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDTO.getEmailId(), signInRequestDTO.getPassword()));
        final String jwt = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        final Map<String, String> jwtAuthResp = new HashMap<>();
        jwtAuthResp.put("token", jwt);
        jwtAuthResp.put("refreshToken", refreshToken);
        return jwtAuthResp;
    }

    @Transactional
    public Map<String, String> refreshToken(final String refreshToken) {
        final String userEmailId = jwtService.extractUserName(refreshToken);
        final User user = userRepository.findByEmailId(userEmailId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (jwtService.validateToken(refreshToken, user)) {
            var jwt = jwtService.generateToken(user);
            final Map<String, String> jwtAuthRep = new HashMap<>();
            jwtAuthRep.put("Token", jwt);
            jwtAuthRep.put("RefreshToken", refreshToken);
            return jwtAuthRep;
        } else {
            throw new UnAuthorizedException(Constant.UNAUTHORIZED);
        }

    }

    public User getById(final int id) {
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.DATA_NULL));
    }

    public List<User> findByAllAndId() {
        return this.userRepository.findAll();
    }

    public String deleteById(final int id) {
        final User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for this id : " + id));
        this.userRepository.delete(user);
        return Constant.REMOVE;
    }


}