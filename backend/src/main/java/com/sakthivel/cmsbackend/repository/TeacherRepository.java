package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {

    Teacher findTeacherByCollegeMailId(String collegeMailId);
}