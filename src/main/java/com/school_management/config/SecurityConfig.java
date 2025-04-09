package com.school_management.config;

import com.school_management.enums.Role;
import com.school_management.util.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/school/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/tutor/**").hasAnyAuthority(Role.TEACHER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/student/**").hasAnyAuthority(Role.STUDENT.name(), Role.ADMIN.name(), Role.TEACHER.name())
                        .requestMatchers("/api/v1/course/**").hasAnyAuthority(Role.ADMIN.name(), Role.TEACHER.name())
                        .requestMatchers("/api/v1/feePayment/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/studentCourse/**").hasAnyAuthority(Role.STUDENT.name(), Role.ADMIN.name(), Role.TEACHER.name())
                        .requestMatchers("/api/v1/tutorCourse/**").hasAnyAuthority(Role.TEACHER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/tutorSalary/**").hasAnyAuthority(Role.ADMIN.name())
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception.accessDeniedHandler(customAccessDeniedHandler))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}