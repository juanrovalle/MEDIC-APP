package com.jrolab.medic_app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultDetailDTO {

    private Integer idDetail;

    private ConsultDTO consult;

    @NotNull
    private String diagnosis;
    
    @NotNull
    private String treament;

}
