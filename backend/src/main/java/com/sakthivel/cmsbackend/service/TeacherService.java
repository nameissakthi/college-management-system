package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.Dao.UserDetailsResponse;
import com.sakthivel.cmsbackend.model.Teacher;
import com.sakthivel.cmsbackend.repository.TeacherRepository;
import com.sakthivel.cmsbackend.util.UtilityFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final Set<String> protectedDataFromTeachers = new HashSet<>(List.of("roles", "collegeMailId", "password"));
    private final ApplicationContext context;

    public TeacherService(
            @Autowired TeacherRepository teacherRepository,
            @Autowired ApplicationContext context
    ) {
        this.teacherRepository = teacherRepository;
        this.context = context;
    }

    public ResponseEntity<ResponseData<List<Teacher>>> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            if(teachers.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "No Content Found"), HttpStatus.OK);
            return new ResponseEntity<>(new ResponseData<>(teachers, true, "Teachers List Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseData<String> toVerifyUserDataForExistence(Teacher teacher) {
        if(teacherRepository.findTeacherByCollegeMailId(teacher.getCollegeMailId())!=null)
            return new ResponseData<>(null, false, "College MailID Already Exists");

        return new ResponseData<>(null, true, "There is no user for the mentioned mail");
    }

    public void addNewTeacher(Teacher teacher) {
        try{
            teacher.setPassword(context.getBean(BCryptPasswordEncoder.class).encode(teacher.getPassword()));
            teacher.setRoles(List.of("TEACHER"));
            teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new RuntimeException("Oops! There is an Exception : "+e.getMessage());
        }
    }

    public ResponseEntity<ResponseData<UserDetailsResponse>> getParticularTeacherUsingEmail(String email) {
        try {
            Teacher teacher = teacherRepository.findTeacherByCollegeMailId(email);
            if(teacher == null) return new ResponseEntity<>(new ResponseData<>(null, false, "Teacher Data Not Found"), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseData<>(new UserDetailsResponse(teacher), true, "Teacher data retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> deleteParticularTeacherUsingEmail(String email) {
        try {
            if(teacherRepository.findTeacherByCollegeMailId(email) == null) return new ResponseEntity<>(new ResponseData<>(null, false, "Teacher Data Not Found"), HttpStatus.NOT_FOUND);

            teacherRepository.deleteTeacherByCollegeMailId(email);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Teacher Deleted Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> updateParticularTeacher(Teacher sourceTeacher) {
        try {
            Teacher targetTeacher = teacherRepository.findById(sourceTeacher.getId()).orElse(null);
            if(targetTeacher == null) new ResponseEntity<>(new ResponseData<>(null, false, "Teacher Data Not found"), HttpStatus.NOT_FOUND);

            UtilityFunctions.CopyAndReplaceFieldsBetweenObjects(sourceTeacher, targetTeacher, protectedDataFromTeachers);

            if (targetTeacher == null) return new ResponseEntity<>(new ResponseData<>(null, false, "An Error Occurred While Saving Data"), HttpStatus.CONFLICT);
            teacherRepository.save(targetTeacher);

            return new ResponseEntity<>(new ResponseData<>(null, true, "Teacher Updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}