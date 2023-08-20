package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.PaymentMethod;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentMethod> list(){
        return manager.createQuery("from Kitchen", PaymentMethod.class).getResultList();
    }

    @Transactional
    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return manager.merge(paymentMethod);
    }

    @Override
    public PaymentMethod search(Long id) {
        return manager.find(PaymentMethod.class, id);
    }

    @Transactional
    @Override
    public void delete(PaymentMethod paymentMethod) {
        paymentMethod = search(paymentMethod.getId());
        manager.remove(paymentMethod);
    }
}