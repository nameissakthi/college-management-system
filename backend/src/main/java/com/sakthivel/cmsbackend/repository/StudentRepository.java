package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findStudentByRollNumber(String rollNumber);

    Student findStudentByCollegeMailId(String collegeMailId);
}