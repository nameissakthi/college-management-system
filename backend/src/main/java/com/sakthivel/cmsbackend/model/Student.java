package com.sakthivel.cmsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sakthivel.cmsbackend.Dao.SemesterMark;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("students")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Student {

    @Id
    private String id;
    private String name;
    private String rollNumber;
    private int age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private List<SemesterMark> semesterMarks;
    private String collegeMailId;
    private String password;
    private List<String> roles;
}