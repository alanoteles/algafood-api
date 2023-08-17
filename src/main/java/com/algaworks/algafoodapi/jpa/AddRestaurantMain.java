package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AddRestaurantMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Brazilian");
        restaurant1.setDeliveryFee(BigDecimal.valueOf(12.3));

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Japanese");
        restaurant2.setDeliveryFee(BigDecimal.valueOf(15.3));

        restaurant1 = restaurantRepository.save(restaurant1);
        restaurant2 = restaurantRepository.save(restaurant2);

        System.out.printf("%d - %s\n", restaurant1.getId(), restaurant1.getName());
        System.out.printf("%d - %s\n", restaurant2.getId(), restaurant2.getName());
    }
}
