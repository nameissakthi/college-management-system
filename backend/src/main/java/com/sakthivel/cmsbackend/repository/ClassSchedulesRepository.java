package com.sakthivel.cmsbackend.repository;

import com.sakthivel.cmsbackend.model.ClassSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSchedulesRepository extends JpaRepository<ClassSchedules, String> {

    @Modifying
    @Query("DELETE FROM class_schedules c WHERE c.department = ?1 AND c.className = ?2")
    Long deleteClassSchedulesOfParticularClass(String department, String className);

    ClassSchedules findClassSchedulesByDayAndDepartmentAndClassName(String day, String department, String className);

    List<ClassSchedules> findAllByDepartmentAndClassName(String department, String className);
}