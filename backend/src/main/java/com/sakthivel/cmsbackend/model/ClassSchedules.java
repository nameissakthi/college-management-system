package com.sakthivel.cmsbackend.model;

import com.sakthivel.cmsbackend.Dao.ClassSchedule;
import jakarta.persistence.*;
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
    private String day;
    private String department;
    private String className;

    @ElementCollection
    @CollectionTable(name = "class_schedule_values", joinColumns = @JoinColumn(name = "class_schedule_id"))
    private List<ClassSchedule> values;
}