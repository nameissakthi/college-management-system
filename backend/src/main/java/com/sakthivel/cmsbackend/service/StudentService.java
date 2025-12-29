package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.repository.StudentRepository;
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
public class StudentService {

    private final StudentRepository studentRepository;
    private final Set<String> protectedDataFromStudents = new HashSet<>(
            List.of("rollNumber", "semesterMarks", "collegeMailId", "role", "id", "attendancePercentage", "monthlyAttendancePercentage"));
    private final ApplicationContext context;

    public StudentService(
            @Autowired StudentRepository studentRepository,
            @Autowired ApplicationContext context
    ) {
        this.studentRepository = studentRepository;
        this.context = context;
    }

    public ResponseEntity<ResponseData<List<Student>>> getAllStudents() {
        try {
            List<Student> students = studentRepository.findAll();
            if(students.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "No Content Found"), HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(new ResponseData<>(students, true, "Students List Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseData<String> toVerifyUserDataForExistence(Student student) {
        if(studentRepository.findStudentByRollNumber(student.getRollNumber())!=null)
            return new ResponseData<>(null, false, "Roll Number Already Exists");

        if(studentRepository.findStudentByCollegeMailId(student.getCollegeMailId())!=null)
            return new ResponseData<>(null, false, "College MailID Already Exists");

        return new ResponseData<>(null, true, "There is no user for the mentioned mail and roll number");
    }

    public void addNewStudent(Student student) {
        try {
            student.setRoles(List.of("STUDENT"));
            student.setAttendancePercentage(0);
            student.setAttendances(null);
            student.setPassword(context.getBean(BCryptPasswordEncoder.class).encode(student.getPassword()));
            studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Oops! There is an Exception : "+e.getMessage());
        }
    }

    public ResponseEntity<ResponseData<Student>> getParticularStudentUsingId(String id) {
        try {
            Student student = studentRepository.findById(id).orElse(null);
            if(student==null) return new ResponseEntity<>(new ResponseData<>(null, false, "Student Not Found"), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseData<>(student, true, "User Data Found"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> deleteParticularStudentUsingId(String id) {
        try {
            if(studentRepository.findById(id).orElse(null) == null) return new ResponseEntity<>(new ResponseData<>(null, false, "Student Not Found"), HttpStatus.NOT_FOUND);

            studentRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Student Account Deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> updateParticularStudent(Student changedStudent) {
        try {
            Student student = studentRepository.findById(changedStudent.getId()).orElse(null);
            if(student == null)
                return new ResponseEntity<>(new ResponseData<>(null, false, "Student Not Found"), HttpStatus.NOT_FOUND);

            changedStudent.setPassword(context.getBean(BCryptPasswordEncoder.class).encode(changedStudent.getPassword()));
            UtilityFunctions.CopyAndReplaceFieldsBetweenObjects(changedStudent, student, protectedDataFromStudents);
            studentRepository.save(student);

            return new ResponseEntity<>(new ResponseData<>(null, true, "User Updated Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}