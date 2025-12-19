//package com.sakthivel.cmsbackend.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
//
//        return httpSecurity
//                .csrf(customizer -> customizer.disable())
//                .authorizeHttpRequests(
//                        request ->
//                                request .requestMatchers("/", "/student/add/", "/teacher/add", "/class-schedules/list", "/class-schedules/get/", "/class-schedules/get/by-keys/").permitAll()
//                                .requestMatchers("/student/get", "/student/update", "/student/delete/").hasRole("STUDENT")
//                                .requestMatchers("/teacher/get", "/teacher/update", "/teacher/delete").hasRole("TEACHER")
//                                .requestMatchers("/class-schedules/**").hasRole("ADMIN")
//                                .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
//}