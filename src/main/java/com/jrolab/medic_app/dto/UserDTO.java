package com.jrolab.medic_app.dto;


import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Integer idUser;


    @NotNull
    private String username;

  
    @NotNull
    private String password;


 //   private List<Role> roles;
}
