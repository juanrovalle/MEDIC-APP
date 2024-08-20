package com.jrolab.service;

import java.util.List;

import com.jrolab.medic_app.model.Consult;
import com.jrolab.medic_app.model.Exam;

public interface ConsultService extends CRUD <Consult, Integer>{
    
    Consult saveTransactional(Consult consult, List<Exam> exams);
}
