package com.jrolab.medic_app.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {

    private Integer idExam;

    @Column(nullable = true, length = 70)
    private String name;

    @Column(nullable = true, length = 150)
    private String description;
}
