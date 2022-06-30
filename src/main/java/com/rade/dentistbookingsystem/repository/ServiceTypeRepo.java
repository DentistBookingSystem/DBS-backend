package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepo extends JpaRepository<ServiceType, Integer> {
     ServiceType findByName(String name);

    @Query(value = "SELECT * FROM ServiceType WHERE id = ?1", nativeQuery = true)
    ServiceType findId(Integer id);
}
