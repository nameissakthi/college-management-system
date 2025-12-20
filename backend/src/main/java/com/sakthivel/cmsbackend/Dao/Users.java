package com.sakthivel.cmsbackend.Dao;

import java.util.List;

public interface Users  {

    String getCollegeMailId();

    String getPassword();

    List<String> getRoles();
}