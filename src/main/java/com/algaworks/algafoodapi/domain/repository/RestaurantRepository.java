package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> list();
    Restaurant search(Long id);
    Restaurant save(Restaurant restaurant);
    void delete(Restaurant restaurant);
}
