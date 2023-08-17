package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> list();
    PaymentMethod search(Long id);
    PaymentMethod save(PaymentMethod paymentMethod);
    void delete(PaymentMethod paymentMethod);
}
