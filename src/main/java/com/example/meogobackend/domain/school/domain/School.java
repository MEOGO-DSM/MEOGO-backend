package com.example.meogobackend.domain.school.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name", nullable = false, length = 30)
    private String schoolName;

    @Column(nullable = false, length = 300)
    private String schoolText;

    @Column(nullable = false)
    private String createDate;

    @Builder
    public School (String schoolName, String schoolText) {
        this.schoolName = schoolName;
        this.schoolText = schoolText;
        this.createDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy .MM .dd"));
    }
}