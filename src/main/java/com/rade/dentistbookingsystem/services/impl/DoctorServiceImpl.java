package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.repository.DoctorRepo;
import com.rade.dentistbookingsystem.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    DoctorRepo doctorRepo;

    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public List<Doctor> findByBranchId(int branch_id) {
        return doctorRepo.findByBranchId(branch_id);
    }

    public Doctor findId(Integer id) {
        return doctorRepo.findId(id);
    }

    @Override
    public int countByBranchId(int branch_id) {
        return doctorRepo.countByBranchId(branch_id);
    }

    @Override
    public List<Integer> findDoctorIdLeastShiftOneDay(String time, int branch_id) {
        return doctorRepo.findDoctorIdLeastShiftOneDay(time, branch_id);
    }
}
