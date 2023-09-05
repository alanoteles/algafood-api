package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class QueryRestaurantMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        for(Restaurant restaurant : restaurants) {
//            System.out.println(restaurant.getName());
            System.out.printf("%s - %f - %s\n", restaurant.getName(), restaurant.getDeliveryFee(),
                    restaurant.getKitchen().getName());
        }
    }
}
