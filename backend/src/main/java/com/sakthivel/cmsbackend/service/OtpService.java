package com.sakthivel.cmsbackend.service;

import com.sakthivel.cmsbackend.Dao.OtpEntityValue;
import com.sakthivel.cmsbackend.Dao.ResponseData;
import com.sakthivel.cmsbackend.model.Student;
import com.sakthivel.cmsbackend.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String, OtpEntityValue> otpEntity = new ConcurrentHashMap<>();
    @Value("${spring.security.otp.expiration-min}")
    private long OTP_EXPIRATION_IN_MIN;

    private final MailService mailService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public OtpService(
            @Autowired MailService mailService,
            @Autowired StudentService studentService,
            @Autowired TeacherService teacherService
    ) {
        this.mailService = mailService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public void sentOtpToMail(String email, Object user, String typeOfUser) {
        String otp = generateOtp();

        otpEntity.put(email, new OtpEntityValue(otp, LocalDateTime.now().plusMinutes(OTP_EXPIRATION_IN_MIN), user, typeOfUser));
        mailService.senMail(
                email,
                "Email Verification OTP From College Management System",
                String.format("""
                            \nFrom College Management System. \nThe OTP for your college mail verification.
                       \s
                            \nOTP : %s
                           \s
                            \nDon't reply do this mail.
                       \s""", otp)
        );
    }

    public ResponseEntity<ResponseData<String>> verifyOtp(String email, String otp) {
        OtpEntityValue value = otpEntity.get(email);

        if(value == null) {
            otpEntity.remove(email);
            return new ResponseEntity<>(new ResponseData<>(null, false, "No pending record found!!!"), HttpStatus.CONFLICT);
        }
        if(value.getExpirationTime().isBefore(LocalDateTime.now())) {
            otpEntity.remove(email);
            return new ResponseEntity<>(new ResponseData<>(null, false, "OTP Expired"), HttpStatus.CONFLICT);
        }
        if(!value.getOtp().equals(otp)) {
            otpEntity.remove(email);
            return new ResponseEntity<>(new ResponseData<>(null, false, "OTP value doesn't match!!!"), HttpStatus.CONFLICT);
        }

        if(value.getTypeOfUser().equals("STUDENT")) studentService.addNewStudent((Student) value.getUser());
        else if(value.getTypeOfUser().equals("TEACHER")) teacherService.addNewTeacher((Teacher) value.getUser());

        otpEntity.remove(email);

        return new ResponseEntity<>(new ResponseData<>(null, true, String.format("%s created successfully!!!", value.getTypeOfUser().toLowerCase())), HttpStatus.OK);
    }

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}