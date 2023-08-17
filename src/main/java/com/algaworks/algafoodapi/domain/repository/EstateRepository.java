package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.model.Kitchen;

import java.util.List;

public interface EstateRepository {

    List<Estate> list();
    Estate search(Long id);
    Estate save(Estate estate);
    void delete(Estate estate);
}
