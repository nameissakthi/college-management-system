package com.sakthivel.cmsbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "class_schedules")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ClassSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Day field is required!!!")
    private String day;
    @NotBlank(message = "Department name is required!!!")
    private String department;
    @NotBlank(message = "Class name requires at-least one character[a-z or A-Z]!!!")
    private String className;

    @ElementCollection
    @CollectionTable(name = "class_schedule_values", joinColumns = @JoinColumn(name = "class_schedule_id"))
    private List<ClassSchedule> values;
}