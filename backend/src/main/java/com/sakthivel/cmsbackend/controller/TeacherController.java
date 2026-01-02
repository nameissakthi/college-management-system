package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.Dao.UserDetailsResponse;
import com.sakthivel.cmsbackend.model.Teacher;
import com.sakthivel.cmsbackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<Teacher>>> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData<UserDetailsResponse>> getParticularTeacherUsingEmail(@RequestParam String email) {
        return teacherService.getParticularTeacherUsingEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<String>> updateParticularTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateParticularTeacher(teacher);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData<String>> deleteParticularTeacherUsingEmail(@RequestParam String email) {
        return teacherService.deleteParticularTeacherUsingEmail(email);
    }
}