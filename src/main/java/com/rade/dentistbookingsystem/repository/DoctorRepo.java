package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    public List<Doctor> findByBranchId(int branch_id);

    @Query(value = "SELECT * FROM Doctor WHERE id = ?1", nativeQuery = true)
    Doctor findId(Integer id);
}
