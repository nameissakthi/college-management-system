package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Attendance;
import com.sakthivel.cmsbackend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(@Autowired AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<Attendance>>> getAttendanceOfAllStudents() {
        return attendanceService.getAttendanceListOfAllStudents();
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData<List<Attendance>>> getAttendanceListOfParticularStudent(@RequestParam String id) {
        return attendanceService.getAttendanceListOfParticularStudent(id);
    }

    @GetMapping("/get/at-month")
    public ResponseEntity<ResponseData<List<Attendance>>> getAttendanceListOfParticularStudentAtMonth(@RequestParam String id, @RequestParam int month) {
        return attendanceService.getAttendanceListOfParticularStudentInMonth(id, month);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<String>> addAttendanceEntryForParticularStudent(@RequestParam String student_id, @RequestBody Attendance attendance) {
        return attendanceService.attendanceEntryForParticularStudent(student_id, attendance);
    }
}