package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Teacher;
import com.sakthivel.cmsbackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<Teacher>>> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}