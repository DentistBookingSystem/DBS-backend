package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Province;
import com.rade.dentistbookingsystem.repository.ProvinceRepo;
import com.rade.dentistbookingsystem.services.ProvinceService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    ProvinceRepo provinceRepo;

    public ProvinceServiceImpl(ProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    @Override
    public List<Province> findAll() {
        return provinceRepo.findAll();
    }

    @Override
    @Query("SELECT  p.districtSet from Province p join p.districtSet")
    public List<Object> getProvinceComponentList() {
        return provinceRepo.getProvinceComponentList();
    }
}
