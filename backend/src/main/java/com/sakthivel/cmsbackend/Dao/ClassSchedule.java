package com.sakthivel.cmsbackend.Dao;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ClassSchedule {

    private String courseName;
    private String assignedTeacher;
}