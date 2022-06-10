package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceRepo extends JpaRepository<Province, Integer> {

    @Query(value = "SELECT  p.districtSet from Province p join p.districtSet")
    List<Object> getProvinceComponentList();
}
