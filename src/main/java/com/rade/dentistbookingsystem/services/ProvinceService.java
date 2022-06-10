package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Province;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceService {

    List<Province> findAll();

    @Query("SELECT p, p.districtSet from Province p join p.districtSet")
    List<Object> getProvinceComponentList();
}
