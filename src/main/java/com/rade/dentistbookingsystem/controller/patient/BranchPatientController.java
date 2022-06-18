package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.HomeComponent;
import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/branch")
public class BranchPatientController {
    @Autowired
    BranchService branchService;

    @GetMapping("")
    public List<Branch> getBranchList(){
        int available = 1;
        return branchService.findByStatus(available);
    }
}
