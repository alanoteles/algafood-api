package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import com.algaworks.algafoodapi.infrastructure.spec.RestaurantWithFreeDeliverySpec;
import com.algaworks.algafoodapi.infrastructure.spec.RestaurantWithLikeNameSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @GetMapping("/search")
    public List<Kitchen> searchByName(String name){

        return kitchenRepository.findAllByNameContaining(name);

    }

    @GetMapping("/taxa-frete")
    public List<Restaurant> byTaxaFrete(BigDecimal minFee, BigDecimal maxFee) {

        return restaurantRepository.findRestaurantsByDeliveryFeeBetween(minFee, maxFee);
    }

    @GetMapping("/name-kitchen")
    public List<Restaurant> byNameAndKitchenId(String name, Long kitchenId){
        return restaurantRepository.searchUsingName(name, kitchenId);
    }

    @GetMapping("/first-restaurant")
    public Optional<Restaurant> first(String name) {
        return restaurantRepository.findFirstRestaurantByNameContaining(name);
    }

    @GetMapping("/top2")
    public List<Restaurant> top2(String name) {
        return restaurantRepository.findTop2RestaurantByNameContaining(name);
    }

    @GetMapping("/exists")
    public boolean restaurantExists(String name){
        return restaurantRepository.existsByName(name);
    }

    @GetMapping("/counting")
    public int kitchenCount(Long kitchenId){
        return restaurantRepository.countByKitchenId(kitchenId);
    }


    @GetMapping("/name-delivery-fee")
    public List<Restaurant> deliveryFee(String name, BigDecimal minFee, BigDecimal maxFee) {
        return restaurantRepository.find(name, minFee, maxFee);
    }

    @GetMapping("/free-delivery")
    public List<Restaurant> freeDelivery(String name) {
        var withFreeDelivery = new RestaurantWithFreeDeliverySpec();
        var withLikeName = new RestaurantWithLikeNameSpec(name);

        return restaurantRepository.findAll(withFreeDelivery.and(withLikeName));

    }
}
