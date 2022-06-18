package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.HomeComponent;
import com.rade.dentistbookingsystem.componentform.JsonPhone;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/branch")
public class BranchPatientController {
    @Autowired
    BranchService branchService;
    @Autowired
    AccountService accountService;
    @PostMapping("")
    public List<Branch> getBranchList(@RequestBody JsonPhone jsonPhone){
        return branchService.getListForChoosing(jsonPhone);
    }

    @PostMapping("recommend")
    public List<Integer> getRecommendList(@RequestBody JsonPhone jsonPhone){
        return branchService.getRecommendList(jsonPhone);
    }
}
