package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kitchen")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kitchen> list(){
        return kitchenRepository.list();
    };

    @GetMapping("/{kitchenId}")
    public Kitchen search(@PathVariable Long kitchenId) {
        return kitchenRepository.search(kitchenId);
    }
}
