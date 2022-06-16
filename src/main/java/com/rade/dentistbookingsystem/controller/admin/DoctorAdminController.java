package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.model.DoctorDTO;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DoctorService;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rade/admin/doctor")
public class DoctorAdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    BranchService branchService;

    @Autowired
    GoogleDriveFileService googleDriveFileService;

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
    public ResponseEntity<?> insertDoctor(@RequestPart @Validated DoctorDTO doctorDTO, @RequestPart MultipartFile url) {
        try {
            String imgUrl = googleDriveFileService.uploadFile(url, "image", true);
            doctorDTO.setUrl(imgUrl);
            Doctor doctor = doctorService.addDoctor(doctorDTO);
            if (doctor == null) throw new Exception();
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    @GetMapping("edit/{id}")
    public ResponseEntity<?> insertDoctor(@RequestPart @Validated DoctorDTO doctorDTO, @RequestPart MultipartFile url, @PathVariable int id) {
        try {
            String imgUrl = googleDriveFileService.uploadFile(url, "image", true);
            doctorDTO.setUrl(imgUrl);
            Doctor doctor = doctorService.editDoctor(doctorDTO, id);
            if (doctor == null) throw new Exception();
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int id) {
        try {
            Doctor doctor = doctorService.deleteDoctor(id);
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


}
