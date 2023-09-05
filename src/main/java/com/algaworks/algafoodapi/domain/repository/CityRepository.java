package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
}
