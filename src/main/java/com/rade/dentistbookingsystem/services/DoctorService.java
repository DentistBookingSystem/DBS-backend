package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findByBranchId(int branch_id);
    Doctor findId(Integer id);

    int countByBranchId(int branch_id);

    List<Integer> findDoctorIdLeastShiftOneDay(String time, int branch_id);
}
