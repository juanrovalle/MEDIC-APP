package com.jrolab.medic_app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {
    
    private Integer idSpeciality;
    @NotNull
    private String name;
    @NotNull
    private String description;

}
