package com.jrolab.service;

import java.util.List;

import com.jrolab.medic_app.model.Patient;

public interface PatientService {
    Patient save(Patient pacient);

    Patient update(Integer id, Patient patient);

    List<Patient> findAll();

    Patient findById(Integer id);

    void delete(Integer id);
}
