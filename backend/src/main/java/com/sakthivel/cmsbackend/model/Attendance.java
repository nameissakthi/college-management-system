package com.sakthivel.cmsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Attendance {

    @NotBlank(message = "Required to specify present or absent!!!")
    @Column(nullable = false)
    private Boolean presentOrAbsent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate date;

    @Override
    public boolean equals(Object attendance) {
            return attendance.getClass().equals(Attendance.class) && this.date.equals(((Attendance) attendance).getDate());
    }
}