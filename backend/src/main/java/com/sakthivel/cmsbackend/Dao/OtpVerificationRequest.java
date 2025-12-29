package com.sakthivel.cmsbackend.Dao;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OtpVerificationRequest {
    private String email;
    private String otp;
}