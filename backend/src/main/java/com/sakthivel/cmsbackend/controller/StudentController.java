package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.Dao.SemesterMark;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

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

    @GetMapping("/get/semester-marks")
    public ResponseEntity<ResponseData<List<SemesterMark>>> getSemesterMArksOfPArticularStudent(@RequestParam String id) {
        return studentService.getSemesterMarksOfParticularStudent(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<String>> addNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<String>> updateParticularStudent(@RequestBody Student student) {
        return studentService.updateParticularStudent(student);
    }

    @PutMapping("/update/add-semester-marks")
    public ResponseEntity<ResponseData<String>> addNewSemesterMarksForParticularStudent(@RequestParam String id, @RequestBody List<SemesterMark> semesterMarks) {
        return studentService.insertNewSemesterMarksForParticularStudent(id, semesterMarks);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData<String>> deleteParticularStudentUsingId(@RequestParam String id) {
        return studentService.deleteParticularStudentUsingId(id);
    }
}