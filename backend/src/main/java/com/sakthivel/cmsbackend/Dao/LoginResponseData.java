package com.sakthivel.cmsbackend.Dao;

import lombok.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class LoginResponseData {

    private String token;
    private UserPrincipal user;
}