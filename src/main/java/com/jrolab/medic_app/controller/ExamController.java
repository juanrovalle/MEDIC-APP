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

import com.jrolab.medic_app.dto.ExamDTO;
import com.jrolab.medic_app.model.Exam;
import com.jrolab.medic_app.service.ExamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService service;

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
 
     private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll() {
        List<ExamDTO> list = service.findAll()
                .stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id) {
        Exam Exam = service.findById(id);

        return ResponseEntity.ok(convertToDto(Exam));
    }

    @PostMapping
    public ResponseEntity<Exam> save(@Valid @RequestBody ExamDTO ExamDTO) {
        Exam obj = service.save(convertToEntity(ExamDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdExam()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@PathVariable("id") Integer id, @RequestBody ExamDTO ExamDTO) {
        ExamDTO.setIdExam(id);
        Exam obj = service.update(id, modelMapper.map(ExamDTO, Exam.class));
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ExamDTO convertToDto(Exam obj) {
        return modelMapper.map(obj, ExamDTO.class);
    }

    private Exam convertToEntity(ExamDTO obj) {
        return modelMapper.map(obj, Exam.class);
    }
}