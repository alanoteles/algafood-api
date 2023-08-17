package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstateRepositoryImpl implements EstateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estate> list(){
        return manager.createQuery("from Estate", Estate.class).getResultList();
    }

    @Transactional
    @Override
    public Estate save(Estate estate) {
        return manager.merge(estate);
    }

    @Override
    public Estate search(Long id) {
        return manager.find(Estate.class, id);
    }

    @Transactional
    @Override
    public void delete(Estate estate) {
        estate = search(estate.getId());
        manager.remove(estate);
    }
}
