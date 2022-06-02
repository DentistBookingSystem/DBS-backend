package com.rade.dentistbookingsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AccountNotificationKey implements Serializable {
    @Column(name = "account_id")
    private int account_id;

    @Column(name = "notification_id")
    private int notification_id;
}
