package com.jrolab.medic_app.controller;

import java.beans.BeanProperty;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import com.jrolab.medic_app.dto.*;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.jrolab.medic_app.model.Consult;
import com.jrolab.medic_app.model.Exam;
import com.jrolab.medic_app.service.ConsultService;
import com.jrolab.medic_app.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final ConsultService service;
    // private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() {
        List<ConsultDTO> list = mapperUtil.mapList(service.findAll(), ConsultDTO.class, "consultMapper");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) {
        Consult Consult = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(Consult, ConsultDTO.class, "consultMapper"));
    }

    @PostMapping
    public ResponseEntity<Consult> save(@Valid @RequestBody ConsultListExamDTO dto) {

        Consult obj = service.saveTransactional(mapperUtil.map(dto.getConsult(), Consult.class, "consultMapper"),
                mapperUtil.mapList(dto.getListExam(), Exam.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@PathVariable("id") Integer id, @RequestBody ConsultDTO ConsultDTO) {
        ConsultDTO.setIdConsult(id);
        Consult obj = service.update(id, mapperUtil.map(ConsultDTO, Consult.class, "consultMapper"));

        return ResponseEntity.ok(mapperUtil.map(obj, ConsultDTO.class, "consultMapper"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // private ConsultDTO convertToDto(Consult obj) {

    // // Return the mapped DTO
    // return modelMapper.map(obj, ConsultDTO.class);
    // }

    // private Consult convertToEntity(ConsultDTO obj) {
    // return modelMapper.map(obj, Consult.class);
    // }

    // public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

    // return source
    // .stream()
    // .map(element -> modelMapper.map(element, targetClass))
    // .toList();
    // }

    // Queries

    @PostMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> searchByOthers(@RequestBody FilterConsultDTO dto) {
        List<Consult> list = service.search(dto.getDni(), dto.getFullName());
        List<ConsultDTO> listDTO = mapperUtil.mapList(list, ConsultDTO.class, "consultMapper");
        return ResponseEntity.ok(listDTO);

    }

    @GetMapping("/search/dates")
    public ResponseEntity<List<ConsultDTO>> searchByDates(
            @RequestParam(value = "date1", defaultValue = "2024-08-01") String date1,
            @RequestParam(value = "date2", defaultValue = "2024-09-30") String date2) {
        List<Consult> list = service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2));
        List<ConsultDTO> listDTO = mapperUtil.mapList(list, ConsultDTO.class, "consultMapper");

        return ResponseEntity.ok(listDTO);

    }

    @GetMapping("/callProcedureNative")
    public ResponseEntity<List<ConsultProcDTO>> CallProcedureNative() {
        List<ConsultProcDTO> list = new ArrayList<>();

        service.callProcedureOrFunctionNative().forEach(e -> {
            ConsultProcDTO dto = new ConsultProcDTO();
            dto.setQuantity(Integer.parseInt(String.valueOf(e.getQuantity())));
            dto.setConsultdate(e.getConsultdate());
            list.add(dto);
        });


        return ResponseEntity.ok(list);
    }

    @GetMapping("/callProcedureProjection")
    public ResponseEntity<List<IConsultDTO>> callProcedureProjection() {


        return ResponseEntity.ok(service.callProcedureOrFunctionProjection());
    }

    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateReports() throws Exception {
        byte[] data = service.generateReport();

        return ResponseEntity.ok(data);
    }
}
