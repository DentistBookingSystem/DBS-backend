package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.exceptions.NotFoundException;
import com.rade.dentistbookingsystem.model.BranchDTO;
import com.rade.dentistbookingsystem.repository.BranchRepo;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepo branchRepo;

    @Autowired
    private DistrictService districtService;

    public BranchServiceImpl(BranchRepo branchRepo) {
        this.branchRepo = branchRepo;
    }

    public List<Branch> findAll() {
        return branchRepo.findAll();
    }

    public Page<Branch> findAll(Pageable pageable) {
        return branchRepo.findAll(pageable);
    }

    @Override
    public <S extends Branch> S save(S entity) {
        return branchRepo.save(entity);
    }

    @Override
    public Branch saveBranch(BranchDTO branchDTO) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date openTime = null;
        Date closeTime = null;
        try {
            openTime = sdf.parse(branchDTO.getOpen_time());
            closeTime = sdf.parse(branchDTO.getClose_time());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (openTime.after(closeTime)) throw new ValidationException("Open time and close time are invalid");
        if (branchRepo.findByName(branchDTO.getName()) != null)
            throw new ValidationException("Branch name is already use");

        else {
            Branch branch = new Branch();
            branch.setName(branchDTO.getName());
            branch.setStatus(branchDTO.getStatus());
            branch.setUrl(branchDTO.getUrl());
            branch.setDistrict(districtService.findById(branchDTO.getDistrict_id()).orElseThrow(() -> new NotFoundException("District is not found")));
            branch.setClose_time(closeTime);
            branch.setOpen_time(openTime);
            return save(branch);
        }

    }

    @Override
    public Branch updateBranch(BranchDTO branchDTO, int id) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date openTime = null;
        Date closeTime = null;
        if (existsById(id) == false) {
            throw new NotFoundException("Branch not found");
        } else {
            Branch branch = branchRepo.getById(id);
            try {
                openTime = sdf.parse(branchDTO.getOpen_time());
                closeTime = sdf.parse(branchDTO.getClose_time());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (openTime.after(closeTime)) throw new ValidationException("Open time and close time are invalid");
            if (branchRepo.findByName(branchDTO.getName()) != null && branchRepo.findId(id).getId() != branchRepo.findByName(branchDTO.getName()).getId())
                throw new ValidationException("Branch name is already use");
            branch.setName(branchDTO.getName());
            branch.setStatus(branchDTO.getStatus());
            branch.setUrl(branchDTO.getUrl());
            branch.setDistrict(districtService.findById(branchDTO.getDistrict_id()).orElseThrow(() -> new NotFoundException("District is not found")));
            branch.setClose_time(closeTime);
            branch.setOpen_time(openTime);
            return save(branch);

        }

    }


    public Optional<Branch> findById(Integer id) {
        return branchRepo.findById(id);
    }

    public boolean existsById(Integer id) {
        return branchRepo.existsById(id);
    }

    public long count() {
        return branchRepo.count();
    }

    public void deleteById(Integer id) {
        branchRepo.deleteById(id);
    }

    public Branch findByName(String name){
        return branchRepo.findByName(name);
    }

    public Branch findByUrl(String url) {
        return branchRepo.findByUrl(url);
    }

    public Branch findId(int id) {
        return branchRepo.findId(id);
    }

    @Override
    public List<Branch> findByStatus(int status) {
        return branchRepo.findByStatus(status);
    }
}
