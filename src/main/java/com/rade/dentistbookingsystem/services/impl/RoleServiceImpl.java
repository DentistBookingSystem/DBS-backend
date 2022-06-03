package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Role;
import com.rade.dentistbookingsystem.repository.RoleRepo;
import com.rade.dentistbookingsystem.services.RoleService;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Optional<Role> findById(Integer integer) {
        return roleRepo.findById(integer);
    }

}
