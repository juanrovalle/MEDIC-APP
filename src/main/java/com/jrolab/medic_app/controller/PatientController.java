package com.jrolab.medic_app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jrolab.medic_app.dto.PatientDTO;
import com.jrolab.medic_app.model.Patient;
import com.jrolab.medic_app.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin()
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
    public ResponseEntity<Patient> save(@Valid @RequestBody PatientDTO patientDTO) {
        Patient obj = service.save(convertToEntity(patientDTO));
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

    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO> findByHateoas(@PathVariable("id") Integer id) {
        EntityModel<PatientDTO> patientDTOEM = EntityModel.of(convertToDto(service.findById(id)));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        patientDTOEM.add(link1.withRel("patient-self-info"));
        WebMvcLinkBuilder link2 = linkTo(methodOn(MedicController.class).findAll());
        patientDTOEM.add(link2.withRel("Medic-self-info"));

        return patientDTOEM;
    }

    private PatientDTO convertToDto(Patient obj) {
        return modelMapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO obj) {
        return modelMapper.map(obj, Patient.class);
    }
}