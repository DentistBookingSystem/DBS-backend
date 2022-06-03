package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Integer integer);
}
