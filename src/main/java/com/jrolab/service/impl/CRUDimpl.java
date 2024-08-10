package com.jrolab.service.impl;

import java.util.List;

import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.service.CRUD;

public abstract class CRUDimpl<T, ID> implements CRUD<T, ID> {

    protected abstract GenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) {

        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }

}
