package com.sakthivel.cmsbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Attendance {

    private boolean presentOrAbsent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Override
    public boolean equals(Object attendance) {
        return this.date.equals(((Attendance) attendance).getDate());
    }
}