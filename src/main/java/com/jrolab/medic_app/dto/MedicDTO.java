package com.jrolab.medic_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicDTO {

    private Integer idMedic;
    @NonNull
    private Integer idSpeciality;
    @NonNull
    private String name;
    @NonNull
    private String lastname;
    @NonNull
    @JsonProperty("NPI")
    private String NPI;
    @NonNull
    public String photoUrl;
}
