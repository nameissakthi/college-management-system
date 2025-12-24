package com.sakthivel.cmsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sakthivel.cmsbackend.Dao.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "teachers")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Teacher implements Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @NotBlank(message = "College Mail Id can't be empty")
    @Column(nullable = false)
    private String collegeMailId;

    @NotBlank(message = "Password can't be empty")
    @Column(nullable = false)
    private String password;
    
    private List<String> roles;
}