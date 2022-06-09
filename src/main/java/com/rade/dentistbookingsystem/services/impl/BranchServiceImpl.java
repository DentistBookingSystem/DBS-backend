package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Branch;
import com.rade.dentistbookingsystem.model.BranchDTO;
import com.rade.dentistbookingsystem.repository.BranchRepo;
import com.rade.dentistbookingsystem.services.BranchService;
import com.rade.dentistbookingsystem.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    public Branch save(BranchDTO branchDTO) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try{
            Branch branch = new Branch(
                    branchDTO.getName(),
                    districtService.getById(branchDTO.getDistrict_id()),
                    sdf.parse(branchDTO.getOpen_time()),
                    sdf.parse(branchDTO.getClose_time()),
                    branchDTO.getStatus(),
                    branchDTO.getUrl());
            return branchRepo.save(branch);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
