package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.componentform.ServiceDiscountComponent;
import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/service")
public class ServiceController {
    @Autowired
    ServiceSv serviceSv;

    @GetMapping("{serviceTypeId}")
    public List<Service> getServiceByServiceTypeId(@PathVariable int serviceTypeId) {
        short available = 1;
        return serviceSv.findByServiceTypeIdAndStatus(serviceTypeId, available);
    }

    @GetMapping("discount/{serviceTypeId}")
    public List<ServiceDiscountComponent> getServiceByServiceTypeIdIncludeDiscount(@PathVariable int serviceTypeId) {
        short available = 1;
        return serviceSv.findByServiceTypeIdAndStatusIncludeDiscount(serviceTypeId, available);
    }

    @GetMapping("")
    public List<Service> getAllService() {
        short available = 1;
        return serviceSv.findAll();
    }
}
