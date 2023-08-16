package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> list(){
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Transactional
    @Override
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    public Kitchen search(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public void delete(Kitchen kitchen) {
        kitchen = search(kitchen.getId());
        manager.remove(kitchen);
    }
}
