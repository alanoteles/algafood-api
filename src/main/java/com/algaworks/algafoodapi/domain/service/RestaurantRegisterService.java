package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Kitchen code not found - %d", kitchenId)
                ));

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }



    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Could not found a Restaurant record with this %d", id));

        } catch (DataIntegrityViolationException e){
            throw new EntityBeingUsedException(
                    String.format("Could not remove Restaurant with ID %d because there is a fk constraint", id)
            );
        }
    }
}
