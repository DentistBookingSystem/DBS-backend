package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.District;

import java.util.List;

public interface DistrictService {
    District getById(Integer integer);

    List<District> findAll();
}
