package com.jrolab.medic_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterConsultDTO {

    private String dni;
    private String fullName;
}
