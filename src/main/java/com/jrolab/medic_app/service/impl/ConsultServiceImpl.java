package com.jrolab.medic_app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.time.LocalDateTime;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.jrolab.medic_app.dto.ConsultProcDTO;
import com.jrolab.medic_app.dto.IConsultDTO;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrolab.medic_app.model.Consult;
import com.jrolab.medic_app.model.Exam;
import com.jrolab.medic_app.repo.ConsultExamRepo;
import com.jrolab.medic_app.repo.ConsultRepo;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.medic_app.service.ConsultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDimpl<Consult, Integer> implements ConsultService {

    private final ConsultRepo consultRepo;
    private final ConsultExamRepo ceRepo;

    @Override
    protected GenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {

        consultRepo.save(consult);
        // add exams to consult
        exams.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam()));
        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullName) {
        return consultRepo.search(dni, fullName);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {

        final long OFFSET_DAYS = 1;
        return consultRepo.searchByDates(date1, date2.plusDays(OFFSET_DAYS));
    }

    @Override
    public List<ConsultProcDTO> callProcedureOrFunctionNative() {
        List<ConsultProcDTO> list = new ArrayList<>();

        consultRepo.callProcedureOrFunctionNative().forEach(e -> {
            ConsultProcDTO dto = new ConsultProcDTO();
            dto.setQuantity(Integer.parseInt(String.valueOf(e[0])));
            dto.setConsultdate(String.valueOf(e[1]));
            list.add(dto);
        });

        return list;
    }


    @Override
    public List<IConsultDTO> callProcedureOrFunctionProjection() {
        return consultRepo.callProcedureOrFunctionProjection();
    }

    @Override
    public byte[] generateReport() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = null;
        Document document = new Document();
        PdfWriter.getInstance(document,byteArrayOutputStream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);

        document.add(chunk);
        document.close();
        data = byteArrayOutputStream.toByteArray();
        return data;
    }
}