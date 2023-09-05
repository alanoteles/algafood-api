package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegisterService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Could not found a kitchen record with this %d", id));

        } catch (DataIntegrityViolationException e){
            throw new EntityBeingUsedException(
                    String.format("Could not remove Kitchen with ID %d because there is a fk constraint", id)
            );
        }
    }
}
