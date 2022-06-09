package com.rade.dentistbookingsystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDTO  implements Serializable {
    private int id;

    @NotNull(message = "Branch's name is required")
    @NotBlank(message = "Branch's name is required")
    @Size(min = 8, max = 30, message = "Branch's name's length from 8 to 30")
    private String name;

    @NotNull(message = "District is required")
    private int district_id;

    private String url;

    @NotNull(message = "Branch's open time is required")
    private String open_time;

    @NotNull(message = "Branch's close time is required")
    private String close_time;

    @NotNull(message = "Branch's status is required")
    private Integer status;
}
