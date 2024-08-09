package com.jrolab.medic_app.model;



import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class ConsultExamPK {
    
    @ManyToOne
    @JoinColumn(name="id_consult")
    private Consult consult;

    @ManyToOne
    @JoinColumn(name="id_exam")
    private Exam exam;

}
