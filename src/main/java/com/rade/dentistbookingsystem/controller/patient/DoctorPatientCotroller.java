package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/doctor")
public class DoctorPatientCotroller {
    @Autowired
    DoctorService doctorService;
    @GetMapping("{branch_id}")
    public List<Doctor> getByBrandId(@PathVariable int branch_id){
        return doctorService.findByBranchId(branch_id);
    }
}
