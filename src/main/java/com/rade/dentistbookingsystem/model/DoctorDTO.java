package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDTO implements Serializable {

    private int id;
    @NotNull(message = "Name of doctor is require")
    @NotEmpty(message = "Description of doctor is require")
    private String name;

    @NotNull(message = "Description of doctor is require")
    @NotEmpty(message = "Description of doctor is require")
    private String description;

    private String url;

    @NotNull(message = "Branch is require")
    private int branchId;

    private int status;

}
