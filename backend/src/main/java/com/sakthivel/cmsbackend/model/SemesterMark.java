package com.sakthivel.cmsbackend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

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