package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
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
