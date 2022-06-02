package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.model.DoctorDTO;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findByBranchId(int branch_id);

    Doctor findId(Integer id);


    Optional<Doctor> findById(Integer integer);

    List<Doctor> findAll();

    <S extends Doctor> S save(S entity);

    // nhận vào id tìm trong cơ sở dữ liêu, không tìm thấy sẽ tiến hành thêm mới
    Doctor addDoctor(DoctorDTO doctorDTO);

    Doctor editDoctor(DoctorDTO doctorDTO, int id) throws Exception;

    void deleteById(Integer integer);
}
