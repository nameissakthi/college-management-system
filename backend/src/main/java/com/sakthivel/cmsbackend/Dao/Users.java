package com.sakthivel.cmsbackend.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface Users  {

    String collegeMailId = null;
    String password = null;
    List<String> roles = null;

    String getCollegeMailId();

    String getPassword();

    List<String> getRoles();
}