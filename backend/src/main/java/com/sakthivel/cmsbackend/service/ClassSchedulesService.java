package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.ClassSchedules;
import com.sakthivel.cmsbackend.repository.ClassSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSchedulesService {

    private ClassSchedulesRepository classSchedulesRepository;

    public ClassSchedulesService(@Autowired ClassSchedulesRepository classSchedulesRepository) {
        this.classSchedulesRepository = classSchedulesRepository;
    }

    public ResponseEntity<ResponseData<List<ClassSchedules>>> getAllClassSchedules() {
        try {
            List<ClassSchedules> schedules = classSchedulesRepository.findAll();
            if(schedules.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "No Content Found"), HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(new ResponseData<>(schedules, true, "All Class Schedules Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<ClassSchedules>> getParticularClassSchedule(String id) {
        try {
            ClassSchedules schedules = classSchedulesRepository.findById(id).orElse(null);
            if(schedules == null) return new ResponseEntity<>(new ResponseData<>(null, false, "No Schedules Found"), HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(new ResponseData<>(schedules, true, "Class Schedules Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> addNewClassSchedule(ClassSchedules classSchedules) {
        try {
            classSchedulesRepository.save(classSchedules);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Class Schedule Stored"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}