package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> list();
    List<Kitchen> searchByName(String name);
    Kitchen search(Long id);
    Kitchen save(Kitchen kitchen);
    void delete(Long id);
}
