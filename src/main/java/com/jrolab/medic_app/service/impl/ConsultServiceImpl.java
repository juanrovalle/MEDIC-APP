package com.jrolab.medic_app.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.time.LocalDateTime;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jrolab.medic_app.dto.ConsultProcDTO;
import com.jrolab.medic_app.dto.IConsultDTO;
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

    public byte[] generateReport() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data;
        List<ConsultProcDTO> listConsultDTO = callProcedureOrFunctionNative();

        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        // Material-inspired colors
        BaseColor primaryColor = new BaseColor(33, 150, 243); // Angular Material blue
        BaseColor secondaryColor = new BaseColor(238, 238, 238); // Light gray for rows

        // Create fonts for title and table
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, primaryColor);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        Font tableFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        // Add a title with Material style
        Paragraph title = new Paragraph("Consults Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10f);
        document.add(title);

        // Create a styled table with 2 columns
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100); // Full-width table
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Set column widths if needed
        table.setWidths(new float[]{1, 1});

        // Header cells with primary color background
        PdfPCell dateHeader = new PdfPCell(new Phrase("Date", headerFont));
        dateHeader.setBackgroundColor(primaryColor);
        dateHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        dateHeader.setPadding(8f);

        PdfPCell quantityHeader = new PdfPCell(new Phrase("Quantity", headerFont));
        quantityHeader.setBackgroundColor(primaryColor);
        quantityHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityHeader.setPadding(8f);

        table.addCell(dateHeader);
        table.addCell(quantityHeader);

        // Populate table with data from ConsultProcDTO list
        for (ConsultProcDTO consult : listConsultDTO) {
            PdfPCell dateCell = new PdfPCell(new Phrase(consult.getConsultdate(), tableFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateCell.setPadding(5f);
            dateCell.setBackgroundColor(secondaryColor); // Alternate row color

            PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(consult.getQuantity()), tableFont));
            quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantityCell.setPadding(5f);
            quantityCell.setBackgroundColor(secondaryColor); // Alternate row color

            table.addCell(dateCell);
            table.addCell(quantityCell);
        }

        document.add(table);
        document.close();

        data = byteArrayOutputStream.toByteArray();
        return data;
    }
}