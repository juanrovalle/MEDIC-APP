package com.jrolab.medic_app.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jrolab.medic_app.model.ConsultDetail;
import com.jrolab.medic_app.model.Medic;
import com.jrolab.medic_app.model.Patient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultListExamDTO {

    @NotNull
    private ConsultDTO consult;
    @NotNull
    private List<ExamDTO> listExam;

}
