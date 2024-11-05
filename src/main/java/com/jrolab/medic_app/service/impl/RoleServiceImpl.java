package com.jrolab.medic_app.service.impl;


import org.springframework.stereotype.Service;

import com.jrolab.medic_app.model.Role;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.medic_app.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDimpl<Role, Integer>  implements RoleService {

    private final GenericRepo<Role, Integer> repo;

    @Override
    protected GenericRepo<Role, Integer> getRepo() {
      return repo;
    }

}
