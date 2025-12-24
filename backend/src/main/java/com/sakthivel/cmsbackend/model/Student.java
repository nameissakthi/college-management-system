package com.sakthivel.cmsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sakthivel.cmsbackend.Dao.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "students")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Student implements Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @NotBlank(message = "Roll number can't be empty")
    @Column(nullable = false)
    private String rollNumber;

    @Range(min = 15, max = 100, message = "Age value range is [15 - 100]")
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @ElementCollection
    @CollectionTable(name = "semester_marks", joinColumns = @JoinColumn(name = "student_id"))
    private List<SemesterMark> semesterMarks;

    @NotBlank(message = "College Mail Id can't be empty")
    @Column(nullable = false)
    private String collegeMailId;

    @NotBlank(message = "Password can't be empty")
    @Column(nullable = false)
    private String password;

    private List<String> roles;
    private Integer attendancePercentage;
    private List<Integer> monthlyAttendancePercentage;

    @ElementCollection
    @CollectionTable(name = "attendance", joinColumns = @JoinColumn(name = "student_id"))
    private List<Attendance> attendances;
}