package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list(){
        return manager.createQuery("from City", City.class).getResultList();
    }

    @Transactional
    @Override
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    public City search(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public void delete(City city) {
        city = search(city.getId());
        manager.remove(city);
    }
}
