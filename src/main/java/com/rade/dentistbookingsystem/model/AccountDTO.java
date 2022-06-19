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
    private int id;
    @NotNull(message = "Full name is required")
    private String full_name;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Date of birth is required")
    private String date_of_birth;
    @NotNull(message = "Gender is required")
    private int gender;
    @NotNull(message = "District is required")
    private int district_id;
    @NotNull(message = "Phone is required")
    private String phone;
    private String email;
    private int status;

    public AccountDTO(String full_name, String password, String date_of_birth, int gender, int district_id, String phone, String email) {
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.district_id = district_id;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

}
