package com.sakthivel.cmsbackend.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class SemesterMark {
    @Range(min = 1, max = 12, message = "The semester values must be between 1 and 12, inclusive")
    private int semester;

    @DecimalMin(value = "1.0", message = "GPA value ranges from 1.0 to 10.0, inclusive")
    @DecimalMax(value = "10.0", message = "GPA value ranges from 1.0 to 10.0, inclusive")
    private float gpa;

    @DecimalMin(value = "1.0", message = "CGPA value ranges from 1.0 to 10.0, inclusive")
    @DecimalMax(value = "10.0", message = "CGPA value ranges from 1.0 to 10.0, inclusive")
    private float cgpa;

    private String courseCode;
    private String courseName;
    private String grade;
    private int gradePoint;
    private String result;
}