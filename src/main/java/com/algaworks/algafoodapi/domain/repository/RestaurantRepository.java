package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
