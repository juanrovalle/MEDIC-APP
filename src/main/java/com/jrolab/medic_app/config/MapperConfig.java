package com.jrolab.medic_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jrolab.medic_app.dto.ConsultDTO;
import com.jrolab.medic_app.dto.MedicDTO;
import com.jrolab.medic_app.model.Consult;
import com.jrolab.medic_app.model.Medic;

@Configuration
public class MapperConfig {

    @Bean(name = "defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean(name = "medicMapper" )
    public ModelMapper medicMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //Escritura
        modelMapper.createTypeMap(MedicDTO.class, Medic.class)
                .addMapping(MedicDTO::getName, (dest, v) -> dest.setName((String) v))
                .addMapping(MedicDTO::getLastname, (dest, v) -> dest.setLastname((String) v))
                .addMapping(MedicDTO::getPhotoUrl, (dest, v) -> dest.setPhotoUrl((String) v));

        //Lectura
        modelMapper.createTypeMap(Medic.class, MedicDTO.class)
                .addMapping(Medic::getName, (dest, v) -> dest.setName((String) v))
                .addMapping(Medic::getLastname, (dest, v) -> dest.setLastname((String) v));

        return modelMapper;
    }

    @Bean(name = "consultMapper")
    public ModelMapper consultMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Consult.class, ConsultDTO.class)
                .addMapping(e -> e.getMedic().getName(), (dest, v) -> dest.getMedic().setName((String) v))
                .addMapping(e -> e.getMedic().getLastname(), (dest, v) -> dest.getMedic().setLastname((String) v));

        return modelMapper;
    }
}
