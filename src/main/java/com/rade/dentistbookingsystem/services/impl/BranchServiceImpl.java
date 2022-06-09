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

import javax.validation.ValidationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepo branchRepo;

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
        try {

            Date openTime = sdf.parse(branchDTO.getOpen_time());
            Date closeTime = sdf.parse(branchDTO.getClose_time());
            if (openTime.after(closeTime)) throw new ValidationException("Open time and close time are invalid");
            Branch branch = new Branch(
                    branchDTO.getName(),
                    districtService.getById(branchDTO.getDistrict_id()),
                    openTime,
                    closeTime,
                    branchDTO.getStatus(),
                    branchDTO.getUrl());
            return save(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Branch updateBranch(BranchDTO branchDTO, int id) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            if (existsById(id) == false) {
                throw new Exception("Branch not found");
            } else {
                Branch branch = branchRepo.getById(id);
                Date openTime = sdf.parse(branchDTO.getOpen_time());
                Date closeTime = sdf.parse(branchDTO.getClose_time());
                if (openTime.after(closeTime)) throw new ValidationException("Open time and close time are invalid");
                branch.setName(branchDTO.getName());
                branch.setStatus(branchDTO.getStatus());
                branch.setUrl(branchDTO.getUrl());
                branch.setDistrict(districtService.getById(branchDTO.getDistrict_id()));
                branch.setClose_time(closeTime);
                branch.setOpen_time(openTime);
                return save(branch);

            }
        } catch (Exception e) {
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

    public Branch findByName(String name) {
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
