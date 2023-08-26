package com.algaworks.algafoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Kitchen kitchen;

    @ManyToOne
    private PaymentMethod paymentMethod;
}
