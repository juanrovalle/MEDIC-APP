package com.jrolab.service.impl;


import org.springframework.stereotype.Service;
import com.jrolab.medic_app.model.Medic;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.medic_app.repo.MedicRepo;
import com.jrolab.service.MedicService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDimpl<Medic, Integer>  implements MedicService{

    private final MedicRepo repo;

    @Override
    protected GenericRepo<Medic, Integer> getRepo() {
      return repo;
    }

}
