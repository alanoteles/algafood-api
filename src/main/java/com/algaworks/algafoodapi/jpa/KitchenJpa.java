package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenJpa {

    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> list(){
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    public Kitchen search(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    public void delete(Kitchen kitchen) {
        kitchen = search(kitchen.getId());
        manager.remove(kitchen);
    }
}
