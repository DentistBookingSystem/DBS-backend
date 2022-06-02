package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepo extends JpaRepository<District, Integer> {
}
