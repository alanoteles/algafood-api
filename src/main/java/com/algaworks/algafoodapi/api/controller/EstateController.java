package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import com.algaworks.algafoodapi.domain.service.EstateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/estates", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstateController {

    @Autowired
    private EstateRepository estateRepository;

    @Autowired
    private EstateRegisterService estateRegisterService;

    @GetMapping
    public List<Estate> list(){
        return estateRepository.findAll();
    }


    @GetMapping("/{estateId}")
    public ResponseEntity<Estate> search(@PathVariable Long estateId) {
        Optional<Estate> estate = estateRepository.findById(estateId);

        return estate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estate add(@RequestBody Estate estate) {
        return estateRegisterService.save(estate);
    }

    @PutMapping("/{estateId}")
    public ResponseEntity<Estate> update(@PathVariable Long estateId,
                                         @RequestBody Estate estate) {

        Optional<Estate> currentEstate = estateRepository.findById(estateId);

        if(currentEstate.isPresent()) {
            BeanUtils.copyProperties(estate, currentEstate.get(), "id");
            Estate updatedState = estateRegisterService.save(currentEstate.get());

            return ResponseEntity.ok(updatedState);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{estateId}")
    public ResponseEntity<Estate> delete(@PathVariable Long estateId) {
        try {
            estateRegisterService.delete(estateId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityBeingUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
