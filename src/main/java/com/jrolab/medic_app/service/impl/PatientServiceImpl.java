package com.jrolab.medic_app.service.impl;


import org.springframework.stereotype.Service;

import com.jrolab.medic_app.model.Patient;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.medic_app.repo.PatientRepo;
import com.jrolab.medic_app.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDimpl<Patient, Integer> implements PatientService {

    private final PatientRepo repo;

    @Override
    protected GenericRepo<Patient, Integer> getRepo() {
     return repo;
    }



    /** Refactor Begins from CRUD BASE
    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Integer id, Patient patient) {
    
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        return repo.findById(id).orElse(new Patient());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
        */
}
