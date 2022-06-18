package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/service")
public class ServicePatientController {
    @Autowired
    ServiceSv serviceSv;

    @GetMapping("{stId}")
    public List<Service> getServiceByServiceTypeIdForPatient(@PathVariable int stId){
        short available = 1;
        return serviceSv.findByServiceTypeIdAndStatus(stId, available);
    }
}
