package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Teacher findTeacherByCollegeMailId(String collegeMailId);

    void deleteTeacherByCollegeMailId(String collegeMailId);
}