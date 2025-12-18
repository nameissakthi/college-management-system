package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.ClassSchedules;
import com.sakthivel.cmsbackend.service.ClassSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-schedules")
public class ClassSchedulesController {

    private ClassSchedulesService classSchedulesService;

    public ClassSchedulesController(@Autowired ClassSchedulesService classSchedulesService) {
        this.classSchedulesService = classSchedulesService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<ClassSchedules>>> getAllClassSchedules() {
        return classSchedulesService.getAllClassSchedules();
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData<ClassSchedules>> getParticularClassSchedule(@RequestParam String id) {
        return classSchedulesService.getParticularClassSchedule(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<String>> addNewClassSchedule(@RequestBody ClassSchedules classSchedules) {
        return classSchedulesService.addNewClassSchedule(classSchedules);
    }
}