package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import com.algaworks.algafoodapi.domain.repository.EstateRepository;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EstateRepository estateRepository;

    public City save(City city) {
        Long estateId = city.getEstate().getId();
        Estate estate = estateRepository.search(estateId);

        if( estate == null) {
            throw new EntityNotFoundException(
                    String.format("Estate code not found - %d", estateId)
            );
        }
        city.setEstate(estate);

        return cityRepository.save(city);
    }



    public void delete(Long id) {
        try {
            cityRepository.delete(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Could not found a City record with this %d", id));

        } catch (DataIntegrityViolationException e){
            throw new EntityBeingUsedException(
                    String.format("Could not remove City with ID %d because there is a fk constraint", id)
            );
        }
    }
}
