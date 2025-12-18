package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.Dao.ClassSchedulesKeys;
import com.sakthivel.cmsbackend.model.ClassSchedules;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSchedulesRepository extends MongoRepository<ClassSchedules, String> {

    @DeleteQuery("{'department' :  ?0, 'className' :  ?0}")
    Long deleteClassSchedulesOfParticularClass(String department, String className);

    ClassSchedules findClassSchedulesByKeys(ClassSchedulesKeys keys);
}