package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.domain.Province;
import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.services.ProvinceService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/province")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    @GetMapping("")
    public List<Province> getProvinceList(){
        return provinceService.findAll();
    }
}
