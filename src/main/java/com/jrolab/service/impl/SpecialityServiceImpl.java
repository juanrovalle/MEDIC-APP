package com.jrolab.service.impl;


import org.springframework.stereotype.Service;
import com.jrolab.medic_app.model.Speciality;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.medic_app.repo.SpecialityRepo;
import com.jrolab.service.SpecialityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl extends CRUDimpl<Speciality, Integer>  implements SpecialityService {

    private final SpecialityRepo repo;

    @Override
    protected GenericRepo<Speciality, Integer> getRepo() {
      return repo;
    }

}
