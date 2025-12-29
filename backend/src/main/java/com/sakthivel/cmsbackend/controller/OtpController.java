package com.sakthivel.cmsbackend.controller;

import com.sakthivel.cmsbackend.Dao.OtpVerificationRequest;
import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp-service")
public class OtpController {

    private final OtpService otpService;

    public OtpController(@Autowired OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseData<String>> otpVerification(@RequestBody OtpVerificationRequest request) {
        return otpService.verifyOtp(request.getEmail(), request.getOtp());
    }
}