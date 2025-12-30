package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.LoginRequestData;
import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.model.Teacher;
import com.sakthivel.cmsbackend.security.JwtUtil;
import com.sakthivel.cmsbackend.service.OtpService;
import com.sakthivel.cmsbackend.service.StudentService;
import com.sakthivel.cmsbackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginAndRegistrationController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final OtpService otpService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginAndRegistrationController(
            @Autowired StudentService studentService,
            @Autowired TeacherService teacherService,
            @Autowired OtpService otpService,
            @Autowired JwtUtil jwtUtil,
            @Autowired AuthenticationManager authenticationManager
            ) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.otpService = otpService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/student/add")
    public ResponseEntity<ResponseData<String>> addNewStudent(@RequestBody Student student) {
        ResponseData<String> response = studentService.toVerifyUserDataForExistence(student);
        if(!response.isSuccess()) return new ResponseEntity<>(new ResponseData<>(null, false, response.getMessage()), HttpStatus.CONFLICT);
        ResponseData<String> mailResponse = otpService.sentOtpToMail(student.getCollegeMailId(), student, "STUDENT");
        return new ResponseEntity<>(mailResponse, mailResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/teacher/add")
    public ResponseEntity<ResponseData<String>> addNewTeacher(@RequestBody Teacher teacher) {
        ResponseData<String> response = teacherService.toVerifyUserDataForExistence(teacher);
        if(!response.isSuccess()) return new ResponseEntity<>(new ResponseData<>(null, false, response.getMessage()), HttpStatus.CONFLICT);
        ResponseData<String> mailResponse = otpService.sentOtpToMail(teacher.getCollegeMailId(), teacher, "TEACHER");
        return new ResponseEntity<>(mailResponse, mailResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<String>> login(@RequestBody LoginRequestData loginRequestData) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestData.getCollegeMailId(),
                        loginRequestData.getPassword()
                )
        );

        String token = jwtUtil.generateToken(loginRequestData.getCollegeMailId());

        return new ResponseEntity<>(new ResponseData<>(token, true, "JWT Token"), HttpStatus.OK);
    }
}