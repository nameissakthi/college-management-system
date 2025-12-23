package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.SemesterMark;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterMarksService {

    private final StudentRepository studentRepository;

    public SemesterMarksService(@Autowired StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<ResponseData<List<SemesterMark>>> getSemesterMarksOfParticularStudent(String id) {
        try{
            List<SemesterMark> semesterMarks = studentRepository.findSemesterMarksOfPArticularStudent(id);
            if (semesterMarks.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "Semester Marks List Where Empty"), HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(new ResponseData<>(semesterMarks, true, "Semester Marks Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> insertNewSemesterMarksForParticularStudent(String id, List<SemesterMark> semesterMark) {
        try{
            Student student = studentRepository.findById(id).orElse(null);
            if (student == null) return new ResponseEntity<>(new ResponseData<>(null, false, "Student Not Found"), HttpStatus.NOT_FOUND);

            List<SemesterMark> semMarks = student.getSemesterMarks();
            semMarks.addAll(semesterMark);
            student.setSemesterMarks(semMarks);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Student Semester Mark Added"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}