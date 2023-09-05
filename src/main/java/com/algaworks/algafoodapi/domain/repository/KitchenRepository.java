package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

//    List<Kitchen> searchByName(String name);

}
