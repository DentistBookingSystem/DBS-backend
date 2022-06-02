package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Appointment SET Status = ?1 WHERE id = ?2", nativeQuery = true)
    void check(Integer status, Integer id);
}
