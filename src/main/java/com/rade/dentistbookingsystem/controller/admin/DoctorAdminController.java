package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.model.DoctorDTO;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rade/admin/doctor")
public class DoctorAdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    BranchService branchService;

    @GetMapping()
    public Optional<Doctor> findById(@RequestParam int id) {
        return doctorService.findById(id);
    }

    @GetMapping("list")
    public List<Doctor> viewDoctorList() {
        try {
            return doctorService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("add")
    public ResponseEntity<?> insertDoctor(@RequestBody @Validated DoctorDTO doctorDTO) {
        try {
            return ResponseEntity.ok(doctorService.addDoctor(doctorDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> insertDoctor(@RequestBody @Validated DoctorDTO doctorDTO, @PathVariable int id) {
        try {
            return ResponseEntity.ok(doctorService.editDoctor(doctorDTO, id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int id) {
        try {
            doctorService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


}
