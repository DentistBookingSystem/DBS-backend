package com.rade.dentistbookingsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Discount_Service")

public class DiscountService implements Serializable {
    @EmbeddedId
    private DiscountServiceKey id;

    @ManyToOne
    @MapsId("service_id")
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @MapsId("discount_id")
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
