package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.ClassSchedules;
import com.sakthivel.cmsbackend.repository.ClassSchedulesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSchedulesService {

    private final ClassSchedulesRepository classSchedulesRepository;

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

    public ResponseEntity<ResponseData<ClassSchedules>> getParticularClassScheduleUsingId(String id) {
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
            if(classSchedulesRepository.findClassSchedulesByDayAndDepartmentAndClassName(
                    classSchedules.getDay(), classSchedules.getDepartment(), classSchedules.getClassName()
            ) != null)
                return new ResponseEntity<>(new ResponseData<>(null, false, "Schedule Already Exists"), HttpStatus.CONFLICT);
            classSchedulesRepository.save(classSchedules);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Class Schedule Stored"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<List<ClassSchedules>>> getParticularClassScheduleByDepartmentAndClassName(String department, String className) {
        try {
            List<ClassSchedules> classSchedulesList = classSchedulesRepository.findAllByDepartmentAndClassName(department, className);
            if(classSchedulesList.isEmpty())return new ResponseEntity<>(new ResponseData<>(null, false, "Schedule List Was Empty"), HttpStatus.CONFLICT);
            return new ResponseEntity<>(new ResponseData<>(classSchedulesList, true, "Class Schedule List Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<ResponseData<Long>> deleteClassSchedulesForParticularClass(String department, String className) {
        try {
            Long numberSchedulesDeleted = classSchedulesRepository.deleteClassSchedulesOfParticularClass(department, className);

            return new ResponseEntity<>(new ResponseData<>(
                    numberSchedulesDeleted, true, String.format("%s - %s Schedules Are Deleted", department, className)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> deleteClassSchedulesUsingId(String id) {
        try {
            if(classSchedulesRepository.findById(id).orElse(null) == null) return new ResponseEntity<>(new ResponseData<>(null, false, "Class Schedule Not Found"), HttpStatus.NOT_FOUND);

            classSchedulesRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Class Schedule Deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Ops! There is an Exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}