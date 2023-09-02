package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
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

    @Override
    public List<Kitchen> searchByName(String name) {
        return manager.createQuery("from Kitchen where name like :name", Kitchen.class)
                .setParameter("name", "%" + name + "%").getResultList();
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Kitchen kitchen = search(id);

        if (kitchen == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(kitchen);
    }
}
