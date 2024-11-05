package com.jrolab.medic_app.service;

import java.util.List;

public interface CRUD<T, ID> {

    T save(T pacient);

    T update(ID id, T T);

    List<T> findAll();

    T findById(ID id);

    void delete(ID id);
}
