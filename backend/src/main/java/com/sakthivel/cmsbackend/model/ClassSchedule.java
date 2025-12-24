package com.sakthivel.cmsbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ClassSchedule {

    @NotBlank(message = "Requires course name!!!")
    @Column(nullable = false)
    private String courseName;

    @NotBlank(message = "Requires assigned teacher name!!!")
    @Column(nullable = false)
    private String assignedTeacher;
}