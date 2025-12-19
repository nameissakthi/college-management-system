package com.sakthivel.cmsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sakthivel.cmsbackend.Dao.SemesterMark;
import com.sakthivel.cmsbackend.Dao.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "students")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Student implements Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String rollNumber;
    private int age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @ElementCollection
    @CollectionTable(name = "semester_marks", joinColumns = @JoinColumn(name = "semester_marks_id"))
    private List<SemesterMark> semesterMarks;
    private String collegeMailId;
    private String password;
    private List<String> roles;
}