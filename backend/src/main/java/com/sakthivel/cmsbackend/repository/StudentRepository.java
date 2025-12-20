package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.Dao.SemesterMark;
import com.sakthivel.cmsbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findStudentByRollNumber(String rollNumber);

    Student findStudentByCollegeMailId(String collegeMailId);

    @Query("SELECT s.semesterMarks FROM students s WHERE s.id = :id")
    List<SemesterMark> findSemesterMarksOfPArticularStudent(@Param("id") String id);
}