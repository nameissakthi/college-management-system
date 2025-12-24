package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.model.Teacher;
import com.sakthivel.cmsbackend.Dao.UserPrincipal;
import com.sakthivel.cmsbackend.repository.StudentRepository;
import com.sakthivel.cmsbackend.repository.TeacherRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public MyUserDetailsService(
            @Autowired StudentRepository studentRepository,
            @Autowired TeacherRepository teacherRepository
    ) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public @Nullable UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Student> students = studentRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        for(Student student : students) {
            if(student.getCollegeMailId().equals(username)) return new UserPrincipal(student);
        }

        for(Teacher teacher : teachers) {
            if(teacher.getCollegeMailId().equals(username)) return new UserPrincipal(teacher);
        }

        throw new UsernameNotFoundException("Username not found : "+username);
    }
}