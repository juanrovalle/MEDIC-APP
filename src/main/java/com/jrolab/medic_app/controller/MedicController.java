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

import com.jrolab.medic_app.dto.MedicDTO;
import com.jrolab.medic_app.model.Medic;
import com.jrolab.medic_app.service.MedicService;
import com.jrolab.medic_app.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {

    private final MedicService service;

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
 
     private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll() {
        List<MedicDTO> list = service.findAll()
                .stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id) {
        Medic Medic = service.findById(id);

        return ResponseEntity.ok(convertToDto(Medic));
    }

    @PostMapping
    public ResponseEntity<Medic> save(@Valid @RequestBody MedicDTO MedicDTO) {
        Medic obj = service.save(convertToEntity(MedicDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@PathVariable("id") Integer id, @RequestBody MedicDTO MedicDTO) {
        MedicDTO.setIdMedic(id);
        Medic obj = service.update(id, modelMapper.map(MedicDTO, Medic.class));
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private MedicDTO convertToDto(Medic obj) {
        return modelMapper.map(obj, MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO obj) {
        return modelMapper.map(obj, Medic.class);
    }
}