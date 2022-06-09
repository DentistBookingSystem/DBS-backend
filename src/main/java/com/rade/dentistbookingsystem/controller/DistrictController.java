package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.domain.District;
import com.rade.dentistbookingsystem.domain.Province;
import com.rade.dentistbookingsystem.services.DistrictService;
import com.rade.dentistbookingsystem.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/district")
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @GetMapping("{province_id}")
    public List<District> getDistrictListByProvinceID(@PathVariable int province_id){
        return districtService.findByProvinceId(province_id);
    }
}
