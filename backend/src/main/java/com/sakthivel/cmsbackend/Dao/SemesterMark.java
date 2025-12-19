package com.sakthivel.cmsbackend.Dao;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.util.List;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class SemesterMark {

    private int semester;
    private float gpa;
    private float cgpa;
    private String courseCode;
    private String courseName;
    private String grade;
    private int gradePoint;
    private String result;
}