package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.model.ServiceDTO;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/service")
public class ServiceAdminController {
    @Autowired
    ServiceSv serviceSv;

    @Autowired
    GoogleDriveFileService googleDriveFileService;


    // những hàm cho service
    @GetMapping("{id}")
    public List<Service> findByID(@PathVariable int id) {
        return serviceSv.findByServiceTypeId(id);
    }

    @GetMapping("list")
    public List<Service> loadActiveService() {
        return serviceSv.loadAllActiveService();
    }

    @PostMapping(value = "add-image")
    public ResponseEntity<?> addServiceImg(@RequestParam MultipartFile url) throws Exception {
        String id = googleDriveFileService.uploadFile(url, "image", true);
        if (id != null)
            return ResponseEntity.ok(id);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping(value = "add-service")
    public ResponseEntity<?> addService(@Valid @RequestBody ServiceDTO serviceDTO) throws Exception {
        try {
            return ResponseEntity.ok(serviceSv.insert(serviceDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();


    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("edit/{id}")
    public ResponseEntity<?> editService(@Valid @RequestBody ServiceDTO serviceDTO, @PathVariable int id) {
        try {
            return ResponseEntity.ok(serviceSv.edit(serviceDTO, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable int id) {
        try {

            return ResponseEntity.ok(serviceSv.deleteService(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

}
