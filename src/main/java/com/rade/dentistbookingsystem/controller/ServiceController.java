package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.services.ServiceSv;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rade/service")
public class ServiceController {
    @Autowired
    ServiceSv serviceSv;
    @Autowired
    ServiceTypeSv serviceTypeSv;

    @GetMapping("{id}")
    public List<Service> list(@PathVariable int id) {
        return serviceSv.findByServiceTypeId(id);
    }

    @GetMapping("/name")
    public List<Service> loadServiceComponentByName(@RequestParam String name) {

        return serviceSv.loadServicesByName(name);


    }
}
