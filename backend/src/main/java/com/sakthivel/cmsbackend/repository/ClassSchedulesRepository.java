package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.ClassSchedules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSchedulesRepository extends MongoRepository<ClassSchedules, String> {
}