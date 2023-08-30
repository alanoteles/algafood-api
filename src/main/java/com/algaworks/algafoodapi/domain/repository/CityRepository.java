package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();
    City search(Long id);
    City save(City city);
    void delete(Long id);
}
