package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO implements Serializable {
    @NotNull(message = "Full name is required")
    private String fullName;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Date of birth is required")
    private String dateOfBirth;
    @NotNull(message = "Gender is required")
    private int gender;
    @NotNull(message = "District is required")
    private int districtId;
    @NotNull(message = "Phone is required")
    private String phone;
    private String email;
    private int status;

    public AccountDTO(String fullName, String password, String dateOfBirth, int gender, int districtId, String phone, String email) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.districtId = districtId;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

}
