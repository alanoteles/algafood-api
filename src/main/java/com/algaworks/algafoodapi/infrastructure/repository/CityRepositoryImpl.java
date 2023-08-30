package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void delete(Long id) {
        City city = search(id);

        if (city == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(city);
    }
}
