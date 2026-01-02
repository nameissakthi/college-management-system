package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.exceptions.AttendanceAlreadyExistsException;
import com.sakthivel.cmsbackend.model.Attendance;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final StudentRepository studentRepository;

    private AttendanceService(@Autowired StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<ResponseData<List<Attendance>>> getAttendanceListOfAllStudents()  {
        try {
            List<Attendance> attendanceList = studentRepository.findAllAttendance();
            if(attendanceList.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "No Attendance Record Found"), HttpStatus.OK);

            return new ResponseEntity<>(new ResponseData<>(attendanceList, true, "Attendance List Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<String>> attendanceEntryForParticularStudent(String student_id, Attendance attendance) {
        try {
            Student student = studentRepository.findById(student_id).orElse(null);
            if(student == null) return new ResponseEntity<>(new ResponseData<>(null, false, "No Student Found"), HttpStatus.NOT_FOUND);

            List<Attendance> attendanceList = student.getAttendances();

            for (Attendance a : attendanceList)
                if (a.equals(attendance)) throw new AttendanceAlreadyExistsException(String.format("The Attendance For The Student At %s Was Already Entered", attendance.getDate()));

            attendanceList.add(attendance);
            student.setAttendances(attendanceList);

            studentRepository.save(student);
            return new ResponseEntity<>(new ResponseData<>(null, true, "Attendance Entered For The Student"), HttpStatus.OK);
        } catch (AttendanceAlreadyExistsException e) {
          return new ResponseEntity<>(new ResponseData<>(null, false, e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<List<Attendance>>> getAttendanceListOfParticularStudent(String id) {
        try {
            List<Attendance> attendanceList = studentRepository.findAllAttendanceOfParticularStudent(id);
            if(attendanceList.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "Attendance List Was Empty"), HttpStatus.OK);

            return new ResponseEntity<>(new ResponseData<>(attendanceList, true, "Attendance List Was Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseData<List<Attendance>>> getAttendanceListOfParticularStudentInMonth(String id, int month) {
        try {
            List<Attendance> attendanceList = studentRepository.findAllAttendanceOfParticularStudentAtMonth(id, month);
            if(attendanceList.isEmpty()) return new ResponseEntity<>(new ResponseData<>(null, false, "Attendance List Was Empty"), HttpStatus.OK);

            return new ResponseEntity<>(new ResponseData<>(attendanceList, true, "Attendance List Was Retrieved"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseData<>(null, false, "Oops! There is an exception\nmessage : "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}