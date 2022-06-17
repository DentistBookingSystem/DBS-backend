package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.model.BranchDTO;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DistrictService;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import com.rade.dentistbookingsystem.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/branch")
public class BranchAdminController {
    @Autowired
    GoogleDriveFileService googleDriveFileService;
    @Autowired
    BranchService branchService;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;


    @GetMapping("list")
    public List<Branch> ListBranch() {
        return branchService.findAll();
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable int id) {

        return branchService.findId(id);
    }

//    @PostMapping("add")
//    public ResponseEntity<?> addBranch(@Valid @RequestPart BranchDTO branchDTO, @RequestPart MultipartFile url) {
//        try {
//            String imageUrl = googleDriveFileService.uploadFile(url, "image", true);
//            branchDTO.setUrl(imageUrl);
//            Branch branch = branchService.saveBranch(branchDTO);
//            if (branch == null) throw new Exception();
//            return ResponseEntity.ok(branch);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//
//    }

    @PostMapping("add")
    public ResponseEntity<?> addBranch(@RequestParam MultipartFile img,
                                       @Valid @ModelAttribute("branchDTO") BranchDTO branchDTO) {
        try {
            String imageUrl = googleDriveFileService.uploadFile(img, "image", true);
            branchDTO.setUrl(imageUrl);
            Branch branch = branchService.saveBranch(branchDTO);
            if (branch == null) throw new Exception();
            return ResponseEntity.ok(branch);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> updateBranch(@RequestParam MultipartFile img,
                                          @Valid @ModelAttribute("branchDTO") BranchDTO branchDTO, @PathVariable int id) {
        try {
            String imageUrl = googleDriveFileService.uploadFile(img, "image", true);
            branchDTO.setUrl(imageUrl);
            Branch branch = branchService.updateBranch(branchDTO, id);
            if (branch == null) throw new Exception();
            return ResponseEntity.ok(branch);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }
}
