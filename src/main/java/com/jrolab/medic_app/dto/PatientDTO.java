package com.jrolab.medic_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Integer idPatient;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 50, message = "{firstname.size")
    private String firstName;
    @Size(min = 2, max = 50)
    @Size(min = 2, max = 50, message = "{lastName.size")
    private String lastName;
    @NotNull
    private String dni;
    @NotNull
    private String address;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phone;
    @NotNull
    @Email
    private String email;

    // @Min(value = 1)
    // @Max(value = 150)
    // private int age;
}
