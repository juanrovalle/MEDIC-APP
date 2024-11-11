package com.jrolab.medic_app.service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

import com.jrolab.medic_app.dto.ConsultProcDTO;
import com.jrolab.medic_app.dto.IConsultDTO;
import com.jrolab.medic_app.model.Consult;
import com.jrolab.medic_app.model.Exam;

public interface ConsultService extends CRUD<Consult, Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exams);

    List<Consult> search(String dni, String fullName);

    List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2);

    List<ConsultProcDTO> callProcedureOrFunctionNative();

    List<IConsultDTO> callProcedureOrFunctionProjection();

    byte[] generateReport() throws Exception;
}