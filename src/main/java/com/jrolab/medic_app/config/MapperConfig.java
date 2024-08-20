package com.jrolab.medic_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.jrolab.medic_app.dto.MedicDTO;
import com.jrolab.medic_app.model.Medic;

@Configuration
public class MapperConfig {
    @Primary
    @Bean(name = "defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean(name = "medicMapper")
    public ModelMapper medicMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Writter
        modelMapper.createTypeMap(MedicDTO.class, Medic.class)
                .addMapping(MedicDTO::getLastname, (dest, v) -> dest.setLastname((String) v))
                .addMapping(MedicDTO::getName, (dest, v) -> dest.setName((String) v));

        // Reader
        modelMapper.createTypeMap(Medic.class, MedicDTO.class)
                .addMapping(Medic::getLastname, (dest, v) -> dest.setLastname((String) v))
                .addMapping(Medic::getName, (dest, v) -> dest.setName((String) v));

        return modelMapper;
    }
}
