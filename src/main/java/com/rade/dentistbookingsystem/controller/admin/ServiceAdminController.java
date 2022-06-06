package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.model.ServiceDTO;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rade/admin/service")
public class ServiceAdminController {
    @Autowired
    ServiceSv serviceSv;
    @Autowired
    ServiceTypeSv serviceTypeSv;
    @Autowired
    GoogleDriveFileService googleDriveFileService;


    // những hàm cho service
    @GetMapping()
    public Optional<Service> findByID(@RequestParam int id) {
        return serviceSv.findById(id);
    }


    @PostMapping(value = "add-service")
    public ResponseEntity<?> addService(@Valid @RequestPart("serviceDTO") ServiceDTO serviceDTO, @RequestPart MultipartFile url) throws Exception {
        try {
            System.out.println(url);
            System.out.println(serviceDTO.toString());
            String imageUrl = googleDriveFileService.uploadFile(url, "image", true);
            serviceDTO.setUrl(imageUrl);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + imageUrl);

            return ResponseEntity.ok(serviceSv.insert(serviceDTO));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();


    }


    @PostMapping(value = "add-image")
    public ResponseEntity<?> addServiceImg(@RequestParam MultipartFile url) throws Exception {
        String id = googleDriveFileService.uploadFile(url, "image", true);
        if (id != null)
            return ResponseEntity.ok(id);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

//    @PostMapping(value = "add-service")
//    public ResponseEntity<?> addService(@Valid @RequestBody ServiceDTO serviceDTO) throws Exception {
//        try {
//
//            return ResponseEntity.ok(serviceSv.insert(serviceDTO));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//
//
//    }

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


    // Hàm trả về ServiceComponent cho admin bao gồm servcie va service type


    @GetMapping("list")
    public List<Service> loadServiceComponent() {
        return serviceSv.findAll();
    }

    // Pagination
    @GetMapping("page")
    public Page<Service> findAllWithPagination() {
        return serviceSv.findAllWithPagination();
    }

    @GetMapping("page/{field}")
    public Page<Service> findAllWithPagination(@PathVariable String field) {
        return serviceSv.findAllWithPaginationAndSorting(field);
    }
}

