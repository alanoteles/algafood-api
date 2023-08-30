package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstateRegisterService {

    @Autowired
    private EstateRepository estateRepository;

    public Estate save(Estate estate) {
        return estateRepository.save(estate);
    }

    public void delete(Long id) {
        try {
            estateRepository.delete(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Could not found a estate record with this %d", id));

        } catch (DataIntegrityViolationException e){
            throw new EntityBeingUsedException(
                    String.format("Could not remove Estate with ID %d because there is a fk constraint", id)
            );
        }
    }
}
