package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstateRepository extends JpaRepository<Estate, Long> {
}
