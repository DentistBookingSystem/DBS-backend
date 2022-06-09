package com.rade.dentistbookingsystem.componentform;

import com.rade.dentistbookingsystem.domain.District;
import com.rade.dentistbookingsystem.domain.Province;
import com.rade.dentistbookingsystem.error.BranchError;
import com.rade.dentistbookingsystem.model.BranchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchComponent {
    private BranchDTO branchDTO;
    private BranchError branchError;
    private List<Province> provinceList;
    private List<District> districtList;
}
