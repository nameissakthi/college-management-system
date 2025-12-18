package com.sakthivel.cmsbackend.model;

import com.sakthivel.cmsbackend.Dao.ClassSchedule;
import com.sakthivel.cmsbackend.Dao.ClassSchedulesKeys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("class_schedule")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ClassSchedules {

    @Id
    private String id;
    private ClassSchedulesKeys keys;
    private List<ClassSchedule> values;
}