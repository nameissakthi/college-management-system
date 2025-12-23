package com.sakthivel.cmsbackend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ClassSchedule {

    private String courseName;
    private String assignedTeacher;
}