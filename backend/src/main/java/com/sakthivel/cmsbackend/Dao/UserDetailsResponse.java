package com.sakthivel.cmsbackend.Dao;

import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.model.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class UserDetailsResponse {

    private String id;
    private String name;
    private String rollNumber;
    private Integer age;
    private LocalDate dateOfBirth;
    private String collegeMailId;
    private List<String> roles;
    private Integer attendancePercentage;

    public UserDetailsResponse(@NonNull Student student) {
        this.id = student.getId();
        this.age = student.getAge();
        this.name = student.getName();
        this.rollNumber = student.getRollNumber();
        this.collegeMailId = student.getCollegeMailId();
        this.dateOfBirth = student.getDateOfBirth();
        this.attendancePercentage = student.getAttendancePercentage();
        this.roles = student.getRoles();
    }

    public UserDetailsResponse(@NonNull Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.collegeMailId = teacher.getCollegeMailId();
        this.dateOfBirth = teacher.getDateOfBirth();
        this.age = teacher.getAge();
        this.roles = teacher.getRoles();
    }
}