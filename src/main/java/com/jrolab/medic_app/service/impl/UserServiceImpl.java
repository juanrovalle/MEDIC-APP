package com.jrolab.medic_app.service.impl;


import org.springframework.stereotype.Service;

import com.jrolab.medic_app.model.User;
import com.jrolab.medic_app.repo.GenericRepo;
import com.jrolab.medic_app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDimpl<User, Integer>  implements UserService {

    private final GenericRepo<User, Integer> repo;

    @Override
    protected GenericRepo<User, Integer> getRepo() {
      return repo;
    }

}
