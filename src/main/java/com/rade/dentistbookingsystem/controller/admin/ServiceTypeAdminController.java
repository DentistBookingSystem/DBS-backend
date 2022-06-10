package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.ServiceType;
import com.rade.dentistbookingsystem.model.ServiceTypeDTO;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
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
        try {

            return ResponseEntity.ok(serviceTypeSv.insert(serviceTypeDTO));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("edit/{id}")
    public ResponseEntity<?> editServiceType(@Valid @RequestBody ServiceTypeDTO serviceTypeDTO, @PathVariable int id) {
        try {

            return ResponseEntity.ok(serviceTypeSv.edit(serviceTypeDTO, id));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }


}
