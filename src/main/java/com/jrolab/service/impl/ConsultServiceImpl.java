package com.jrolab.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jrolab.medic_app.model.Consult;
import com.jrolab.medic_app.model.Exam;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.service.ConsultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDimpl<Consult, Integer> implements ConsultService {

  private final GenericRepo<Consult, Integer> consultRepo;

  @Override
  protected GenericRepo<Consult, Integer> getRepo() {
    return consultRepo;
  }

  @Override
  public Consult saveTransactional(Consult consult, List<Exam> exams) {

    consultRepo.save(consult);
    // add exams to consult
    // exams.forEach(ex -> );
    return consult;
  }

}
