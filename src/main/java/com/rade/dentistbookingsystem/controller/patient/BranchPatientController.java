package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.HomeComponent;
import com.rade.dentistbookingsystem.componentform.JsonPhone;
import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/branch")
public class BranchPatientController {
    @Autowired
    BranchService branchService;
    @Autowired
    AccountService accountService;
    @GetMapping("")
    public List<Branch> getBranchList(){
        int available = 1;
        return branchService.findByStatus(available);
    }

//    @PostMapping("recommend")
//    public List<Integer> getRecommendList(@RequestBody JsonPhone jsonPhone){
//
//        int available = 1;
//        List<Branch> branchList = branchService.findByStatus(available);
//        for (Branch branch : branchList) {
//
//        }
//    }
}
