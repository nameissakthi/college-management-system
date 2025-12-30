package com.sakthivel.cmsbackend.Dao;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class LoginRequestData {

    private String collegeMailId;
    private String password;
}