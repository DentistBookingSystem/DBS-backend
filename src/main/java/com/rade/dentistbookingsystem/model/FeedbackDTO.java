package com.rade.dentistbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDTO implements Serializable {

    private int id;
    @NotNull
    private int service_id;
    @NotNull
    private int account_id;
    @NotNull
    private String time;
    @NotNull
    private String content;

    private int status;

    public FeedbackDTO(int service_id, int account_id, String time, String content, int status) {
        this.service_id = service_id;
        this.account_id = account_id;
        this.time = time;
        this.content = content;
        this.status = status;
    }
}
