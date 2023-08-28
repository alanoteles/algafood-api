package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estates", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstateController {

    @Autowired
    private EstateRepository estateRepository;

    @GetMapping
    public List<Estate> list(){
        return estateRepository.list();
    }


    @GetMapping("/{estateId}")
    public ResponseEntity<Estate> search(@PathVariable Long estateId) {
        Estate estate = estateRepository.search(estateId);

        if(estate != null){
            return ResponseEntity.ok(estate);
        }
        return ResponseEntity.notFound().build();
    }
}
