package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.ServiceType;
import com.rade.dentistbookingsystem.model.ServiceTypeDTO;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/service-type")
public class ServiceTypeAdminController {
    @Autowired
    ServiceTypeSv serviceTypeSv;

    @GetMapping("list")
    public List<ServiceType> findAll() {
        return serviceTypeSv.findAll();
    }

    // add service type cho admin
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("add")
    public ResponseEntity<?> addServiceType(@Valid @RequestBody ServiceTypeDTO serviceTypeDTO) {
            ServiceType serviceType = serviceTypeSv.insert(serviceTypeDTO);
            if (serviceType != null)
                return ResponseEntity.ok(serviceType);
            else
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("edit/{id}")
    public ResponseEntity<?> editServiceType(@Validated @RequestBody ServiceTypeDTO serviceTypeDTO, @PathVariable int id) {
            return ResponseEntity.ok(serviceTypeSv.edit(serviceTypeDTO, id));

    }


}
