package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.model.DoctorDTO;
import com.rade.dentistbookingsystem.repository.DoctorRepo;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    DoctorRepo doctorRepo;
    @Autowired
    BranchService branchService;

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
    public Optional<Doctor> findById(Integer integer) {
        return doctorRepo.findById(integer);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }

    @Override
    public <S extends Doctor> S save(S entity) {
        return doctorRepo.save(entity);
    }

    // nhận vào id tìm trong cơ sở dữ liêu, không tìm thấy sẽ tiến hành thêm mới
    @Override
    public Doctor addDoctor(DoctorDTO doctorDTO) {

        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setDescription(doctorDTO.getDescription());
        doctor.setUrl(doctorDTO.getUrl());
        Branch branch = branchService.findById(doctorDTO.getBranch_id()).orElseThrow(() -> new RuntimeException("Can not find branch"));
        doctor.setBranch(branch);
        return save(doctor);

    }


    @Override
    public Doctor editDoctor(DoctorDTO doctorDTO, int id) throws Exception {
        Optional<Doctor> doctorData = findById(id);
        if (doctorData.isPresent()) {
            Doctor doctor = doctorData.get();
            doctor.setName(doctorDTO.getName());
            doctor.setDescription(doctorDTO.getDescription());
            doctor.setUrl(doctorDTO.getUrl());
            Branch branch = branchService.findById(doctorDTO.getBranch_id()).orElseThrow(() -> new RuntimeException("Can not find branch"));
            doctor.setBranch(branch);
            return save(doctor);
        }
        return null;

    }

    @Override
    public void deleteById(Integer integer) {
        doctorRepo.deleteById(integer);
    }
}
