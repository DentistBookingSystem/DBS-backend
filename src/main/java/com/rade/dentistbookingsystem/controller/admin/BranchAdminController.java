package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.BranchComponent;
import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.error.BranchError;
import com.rade.dentistbookingsystem.model.BranchDTO;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DistrictService;
import com.rade.dentistbookingsystem.services.GoogleDriveFileService;
import com.rade.dentistbookingsystem.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/branch")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class BranchAdminController {
    @Autowired
    GoogleDriveFileService googleDriveFileService;
    @Autowired
    BranchService branchService;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor((true));
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);}



    @GetMapping("list")
    public List<Branch> list(){ return branchService.findAll(); }

    @GetMapping("add")
    public BranchComponent showBranchForm(){
        return new BranchComponent(
                new BranchDTO(),
                new BranchError(),
                provinceService.findAll(),
                districtService.findAll()
        );
    }

    @PostMapping("add")
    public BranchComponent insert(
            @RequestPart MultipartFile photo,
            @Valid @ModelAttribute("branchDTO") BranchDTO branchDTO,
            BindingResult bindingResult) throws Exception{


        boolean valid = true;
        BranchError branchError = new BranchError();
        //name
        String name = branchDTO.getName();
        if(branchService.findByName(name) != null){
            branchError.setNameError("Branch's name is existed");
            valid = false;
        }
        //url
        if(branchDTO.getUrl() == null){
            if(photo.getOriginalFilename() == ""){
                branchError.setUrlError("Branch's image is required");
                valid = false;}
            else {
                if (!(photo.getOriginalFilename().endsWith(".jpg") || photo.getOriginalFilename().endsWith(".png"))){
                    branchError.setUrlError("Branch's image must be JPG or PNG file");
                    valid = false;}
                else {
                    branchDTO.setUrl(googleDriveFileService.uploadFile(photo, "image", true));
                }}}
        else{
            if(photo.getOriginalFilename().length()!=0){
                if (!(photo.getOriginalFilename().endsWith(".jpg") || photo.getOriginalFilename().endsWith(".png"))){
                    branchError.setUrlError("Branch's image must be JPG or PNG file");
                    valid = false;}
                else {
                    googleDriveFileService.deleteFile(branchDTO.getUrl());
                    branchDTO.setUrl(googleDriveFileService.uploadFile(photo, "image", true));}}}



        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(f -> {
                switch (f.getField()){
                    case "name":{
                        branchError.setNameError(f.getDefaultMessage());
                        break;
                    }
                    case "district_id":{
                        branchError.setDistrict_idError(f.getDefaultMessage());
                        break;
                    }
                    case "open_time":{
                        branchError.setOpen_timeError(f.getDefaultMessage());
                        break;
                    }
                    case "close_time":{
                        branchError.setClose_timeError(f.getDefaultMessage());
                        break;
                    }
                    case "status":{
                        branchError.setStatusError(f.getDefaultMessage());
                        break;
                    }
                }}
            );
        }

        if(!valid || bindingResult.hasErrors()){
            return new BranchComponent(
                    branchDTO,
                    branchError,
                    provinceService.findAll(),
                    districtService.findAll());}
        else{
            branchService.save(branchDTO);
            return null;}
    }
}
