package com.jrolab.medic_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jrolab.service.PatientService;
import lombok.RequiredArgsConstructor;

import com.jrolab.medic_app.dto.PatientDTO;
import com.jrolab.medic_app.model.Patient;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() {
        List<PatientDTO> list = service.findAll()
                .stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) {
        Patient patient = service.findById(id);

        return ResponseEntity.ok(convertToDto(patient));
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody PatientDTO patiendDTO) {
        Patient obj = service.save(convertToEntity(patiendDTO));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdPatient()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable("id") Integer id, @RequestBody PatientDTO patientDTO) {
        patientDTO.setIdPatient(id);
        Patient obj = service.update(id, modelMapper.map(patientDTO, Patient.class));
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PatientDTO convertToDto(Patient obj) {
        return modelMapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO obj) {
        return modelMapper.map(obj, Patient.class);
    }
}