/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.componentform.HomeComponent;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Masterkien
 */
@RestController
@CrossOrigin
@RequestMapping("rade/home")
public class HomeController {
    @Autowired
    BranchService branchService;
    @Autowired
    ServiceTypeSv serviceTypeSv;

    @GetMapping("")
    public HomeComponent list(Model model) {
        int available = 1;
        return new HomeComponent(serviceTypeSv.findAll(), branchService.findByStatus(available));
    }
}
