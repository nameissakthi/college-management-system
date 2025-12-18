package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<Student>>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData<Student>> getParticularStudent(@RequestParam String id) {
        return studentService.getParticularStudentUsingId(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<String>> addNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<String>> updateParticularStudent(@RequestBody Student student) {
        return studentService.updateParticularStudent(student);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData<String>> deleteParticularStudentUsingId(@RequestParam String id) {
        return studentService.deleteParticularStudentUsingId(id);
    }
}