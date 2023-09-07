package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>,
                                              RestaurantRepositoryQueries,
                                              JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> findRestaurantsByDeliveryFeeBetween(BigDecimal minFee, BigDecimal maxFee);

    // Implemented in RestaurantRepositoryImpl file
    List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee);

//    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> searchUsingName(String name,@Param("id") Long kitchenId);

//    List<Restaurant> findByNameContainingAndKitchenId(String name, Long id);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    List<Restaurant> findTop2RestaurantByNameContaining(String name);

    boolean existsByName(String name);

    int countByKitchenId(Long id);
}
