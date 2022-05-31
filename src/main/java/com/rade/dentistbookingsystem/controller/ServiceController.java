package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import com.rade.dentistbookingsystem.model.ServiceDTO;
import com.rade.dentistbookingsystem.model.ServiceTypeDTO;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("service-type")
public class ServiceController {
    @Autowired
    ServiceSv serviceSv;
    @Autowired
    ServiceTypeSv serviceTypeSv;

    @Autowired
    GoogleDriveFileService googleDriveFileService;

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
    // ko xoa đc vi là bảng gốc, tham chiếu cho service
//    @GetMapping("delete/{id}")
//        public ResponseEntity<?> deleteServiceType(@PathVariable int id){
//        Optional<ServiceType> serviceType = serviceTypeSv.findById(id);
//        if (serviceType.isPresent()) { // có tồn tại thì cho delete
//            serviceTypeSv.deleteById(id);
//            return ResponseEntity.ok(id);
//
//
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//
//        }
//    }

    // những hàm cho service
    @GetMapping("service/{id}")
    public List<Service> findByID(@PathVariable int id) {
        return serviceSv.findByServiceTypeId(id);
    }

    @GetMapping("service/list")
    public List<Service> loadActiveService() {
        return serviceSv.loadAllActiveService();
    }

    @PostMapping(value = "/service/add-image")
    public ResponseEntity<?> addServiceImg(@RequestParam MultipartFile url) throws Exception {
        String id = googleDriveFileService.uploadFile(url, "image", true);
        if (id != null)
            return ResponseEntity.ok(id);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping(value = "/service/add-service")
    public ResponseEntity<?> addService(@Valid @RequestBody ServiceDTO serviceDTO) throws Exception {
        try {

            return ResponseEntity.ok(serviceSv.insert(serviceDTO));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();


    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("service/edit/{id}")
    public ResponseEntity<?> editService(@Valid @RequestBody ServiceDTO serviceDTO, @PathVariable int id) {
        try {

            System.out.println(serviceDTO);
            return ResponseEntity.ok(serviceSv.edit(serviceDTO, id));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }

    @GetMapping("service/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable int id) {
        try {

            return ResponseEntity.ok(serviceSv.deleteService(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


}
