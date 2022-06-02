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
    @JsonIgnore
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int status;
}
