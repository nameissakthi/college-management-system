package com.sakthivel.cmsbackend.Dao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SemesterMark {

    private int semester;
    private float gpa;
    private float cgpa;
    private List<CourseResult> courseResults;
}