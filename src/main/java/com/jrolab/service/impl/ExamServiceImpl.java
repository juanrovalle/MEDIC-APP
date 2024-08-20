package com.jrolab.service.impl;


import org.springframework.stereotype.Service;

import com.jrolab.medic_app.model.Exam;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.service.ExamService;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDimpl<Exam, Integer>  implements ExamService {

    private final GenericRepo<Exam, Integer> repo;

    @Override
    protected GenericRepo<Exam, Integer> getRepo() {
      return repo;
    }

}
