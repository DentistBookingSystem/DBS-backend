package com.rade.dentistbookingsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
