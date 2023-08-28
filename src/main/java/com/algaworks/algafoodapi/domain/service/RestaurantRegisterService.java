package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {

        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.search(kitchenId);

        if( kitchen == null) {
            throw new EntityNotFoundException(
                    String.format("Kitchen code not found - %d", kitchenId)
            );
        }

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }
}
