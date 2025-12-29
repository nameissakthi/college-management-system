package com.sakthivel.cmsbackend.Dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OtpEntityValue {

    private String Otp;
    private LocalDateTime expirationTime;
    private Object user;
    private String typeOfUser;
}