package com.jrolab.medic_app.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jrolab.medic_app.dto.SpecialityDTO;
import com.jrolab.medic_app.model.Speciality;
import com.jrolab.service.SpecialityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> findAll() {
        List<SpecialityDTO> list = service.findAll()
                .stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> findById(@PathVariable("id") Integer id) {
        Speciality Speciality = service.findById(id);

        return ResponseEntity.ok(convertToDto(Speciality));
    }

    @PostMapping
    public ResponseEntity<Speciality> save(@Valid @RequestBody SpecialityDTO SpecialityDTO) {
        Speciality obj = service.save(convertToEntity(SpecialityDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdSpeciality()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDTO> update(@PathVariable("id") Integer id,
            @RequestBody SpecialityDTO SpecialityDTO) {
        SpecialityDTO.setIdSpeciality(id);
        Speciality obj = service.update(id, modelMapper.map(SpecialityDTO, Speciality.class));
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private SpecialityDTO convertToDto(Speciality obj) {
        return modelMapper.map(obj, SpecialityDTO.class);
    }

    private Speciality convertToEntity(SpecialityDTO obj) {
        return modelMapper.map(obj, Speciality.class);
    }
}