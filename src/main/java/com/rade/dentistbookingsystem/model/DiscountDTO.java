package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountDTO implements Serializable {
    private int id;
    @NotNull(message = "the one who create discount is required")
    private int account_id;
    @NotNull
    private String name;
    @NotNull
    private float percentage;
    @NotNull
    private String description;
    @NotNull
    private int status;
    @NotNull
    private String start_date;
    @NotNull
    private String end_date;


    public DiscountDTO(int account_id, String name, float percentage, String description, int status, String start_date, String end_date) {
        this.account_id = account_id;
        this.name = name;
        this.percentage = percentage;
        this.description = description;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;

    }
}
