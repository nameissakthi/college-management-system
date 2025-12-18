package com.sakthivel.cmsbackend.model;

import com.sakthivel.cmsbackend.Dao.TimeTable;
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
    private TimeTable.ClassSchedulesKeys keys;
    private List<TimeTable.ClassSchedule> values;
}