package com.jrolab.medic_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@IdClass(ConsultExamPK.class)
public class ConsultExam {
    @Id
    private Consult consult;

    @Id
    private Exam exam;

    @Column
    private String text;

}
