package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Teacher;
import com.sakthivel.cmsbackend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(
            @Autowired TeacherRepository teacherRepository
    ) {
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<ResponseData<List<Teacher>>> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            if(teachers.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "No Content Found"), HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(new ResponseData<>(teachers, true, "Teachers List Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}