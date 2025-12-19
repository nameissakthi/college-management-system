package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.Users;
import com.sakthivel.cmsbackend.repository.StudentRepository;
import com.sakthivel.cmsbackend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Users> users = new ArrayList<>();

        users.addAll(studentRepository.findAll());
        users.addAll(teacherRepository.findAll());

        System.out.println(users);

        return null;
    }
}