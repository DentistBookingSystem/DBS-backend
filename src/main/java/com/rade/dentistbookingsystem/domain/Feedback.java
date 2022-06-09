package com.rade.dentistbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Feedback")
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Feedback vs service
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;

    @Column(nullable = false, columnDefinition = "nvarchar(MAX)")
    private String content;

    @Column(nullable = false)
    private int status;

    public Feedback(Service service, Account account, Date time, String content, int status){
        this.service = service;
        this.account = account;
        this.time = time;
        this.content = content;
        this.status = status;
    }
}
