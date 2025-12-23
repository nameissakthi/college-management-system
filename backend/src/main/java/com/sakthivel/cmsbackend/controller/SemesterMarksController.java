package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.SemesterMark;
import com.sakthivel.cmsbackend.service.SemesterMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semester")
public class SemesterMarksController {

    private final SemesterMarksService semesterMarksService;

    public SemesterMarksController(@Autowired SemesterMarksService semesterMarksService) {
        this.semesterMarksService = semesterMarksService;
    }

    @GetMapping("/get/semester-marks")
    public ResponseEntity<ResponseData<List<SemesterMark>>> getSemesterMArksOfPArticularStudent(@RequestParam String id) {
        return semesterMarksService.getSemesterMarksOfParticularStudent(id);
    }

    @PutMapping("/update/add-semester-marks")
    public ResponseEntity<ResponseData<String>> addNewSemesterMarksForParticularStudent(@RequestParam String id, @RequestBody List<SemesterMark> semesterMarks) {
        return semesterMarksService.insertNewSemesterMarksForParticularStudent(id, semesterMarks);
    }
}