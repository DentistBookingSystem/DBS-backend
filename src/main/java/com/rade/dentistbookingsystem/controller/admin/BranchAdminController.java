package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.exceptions.NotFoundException;
import com.rade.dentistbookingsystem.model.BranchDTO;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DistrictService;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import com.rade.dentistbookingsystem.services.ProvinceService;
import com.rade.dentistbookingsystem.utils.image.ImageService;
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

    @Autowired
    ImageService imageService;

    @GetMapping("/{id}")
    public Branch findById(@PathVariable int id) {
        Branch branch = branchService.findId(id);
        if (branch != null) {
            return branch;
        } else throw new NotFoundException("Branch is not found ");

    }

    @PostMapping(value = "add-image")
    public ResponseEntity<?> addBranchImg(@RequestParam MultipartFile url) throws Exception {
        String id = imageService.validateAndDownload(url);
        if (id != null)
            return ResponseEntity.ok(id); // lấy id gán vào cột url của serviceDTO sẽ gửi lên requeest
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


    @GetMapping("list")
    public List<Branch> ListBranch() {
        return branchService.findAll();
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
    public ResponseEntity<?> addBranch(@Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.saveBranch(branchDTO));


    }

//    @PostMapping("update/{id}")
//    public ResponseEntity<?> updateBranch(@Valid @RequestPart BranchDTO branchDTO, @RequestPart MultipartFile url, @PathVariable int id) {
//        try {
//            String imageUrl = googleDriveFileService.uploadFile(url, "image", true);
//            branchDTO.setUrl(imageUrl);
//            Branch branch = branchService.updateBranch(branchDTO, id);
//            if (branch == null) throw new Exception();
//            return ResponseEntity.ok(branch);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//
//    }
//}

    @PostMapping("edit")
    public ResponseEntity<?> updateBranch(@Valid @RequestBody BranchDTO branchDTO) {


        return ResponseEntity.ok(branchService.updateBranch(branchDTO, branchDTO.getId()));


    }
}
