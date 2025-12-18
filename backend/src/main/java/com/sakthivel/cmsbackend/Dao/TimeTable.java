package com.sakthivel.cmsbackend.Dao;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class TimeTable {

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class ClassSchedulesKeys{
        private String day;
        private String department;
        private char className;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class ClassSchedule {
        private String courseName;
        private String assignedTeacher;
    }
}