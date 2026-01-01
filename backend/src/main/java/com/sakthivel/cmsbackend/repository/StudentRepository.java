package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.Attendance;
import com.sakthivel.cmsbackend.model.SemesterMark;
import com.sakthivel.cmsbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findStudentByRollNumber(String rollNumber);

    Student findStudentByCollegeMailId(String collegeMailId);

    void deleteStudentByRollNumber(String rollNumber);

    @Query("SELECT s.semesterMarks FROM students s WHERE s.id = :id")
    List<SemesterMark> findSemesterMarksOfPArticularStudent(@Param("id") String id);

    @Query("SELECT s.attendances FROM students s")
    List<Attendance> findAllAttendance();

    @Query("SELECT s.attendances FROM students s WHERE s.id = :id")
    List<Attendance> findAllAttendanceOfParticularStudent(@Param("id") String id);

    @Query("SELECT a FROM students s JOIN s.attendances a WHERE s.id = :id AND extract(MONTH FROM a.date) = :month")
    List<Attendance> findAllAttendanceOfParticularStudentAtMonth(String id, int month);
}