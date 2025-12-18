package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findStudentByRollNumber(String rollNumber);

    Student findStudentByCollegeMailId(String collegeMailId);
}