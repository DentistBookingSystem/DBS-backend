package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.model.DoctorDTO;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findByBranchId(int branch_id);

    Doctor findId(Integer id);

    int countByBranchId(int branch_id);

    Optional<Doctor> findById(Integer integer);

    // Use for admin
    List<Doctor> findAll();

    Doctor addDoctor(DoctorDTO doctorDTO);

    Doctor editDoctor(DoctorDTO doctorDTO, int id) throws Exception;

    Doctor deleteDoctor(int id);
}
