package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estate", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstateController {

    @Autowired
    private EstateRepository estateRepository;

    @GetMapping
    public List<Estate> list(){
        return estateRepository.list();
    }


    @GetMapping("/{estateId}")
    public Estate search(@PathVariable Long estateId) {
        return estateRepository.search(estateId);
    }
}
