package com.algaworks.algafoodapi.api.controller;


import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import com.algaworks.algafoodapi.domain.service.RestaurantRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantRegisterService restaurantRegisterService;



    @GetMapping
    public List<Restaurant> list(){
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> search(@PathVariable Long restaurantId){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegisterService.save(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
//            return ResponseEntity.created(URI.create("/" + restaurant.getId())).build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId,
                                    @RequestBody Restaurant restaurant) {
        try {
            Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);

            if (currentRestaurant.isPresent()) {
                BeanUtils.copyProperties(restaurant, currentRestaurant.get(), "id");
                Restaurant updatedRestaurant = restaurantRegisterService.save(currentRestaurant.get());

                return ResponseEntity.ok(updatedRestaurant);
            }
            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> updatePartially(@PathVariable Long restaurantId,
                                                      @RequestBody Map<String, Object> fields) {

        Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);

        if( currentRestaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, currentRestaurant.get());

        return update(restaurantId, currentRestaurant.get());

    }


    private void merge(Map<String, Object> sourceFields, Restaurant targetRestaurant) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant sourceRestaurant = objectMapper.convertValue(sourceFields, Restaurant.class);

        sourceFields.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, sourceRestaurant);

            ReflectionUtils.setField(field, targetRestaurant, newValue);
        });

    }



    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> delete(@PathVariable Long restaurantId) {
        try {
            restaurantRegisterService.delete(restaurantId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        } catch (EntityBeingUsedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
