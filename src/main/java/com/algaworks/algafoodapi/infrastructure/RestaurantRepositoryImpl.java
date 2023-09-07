package com.algaworks.algafoodapi.infrastructure;

import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee){

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");
            predicates.add(namePredicate);
        }

        if (minFee != null) {
            Predicate minFeePredicate = builder.greaterThanOrEqualTo(root.get("deliveryFee"), minFee);
            predicates.add(minFeePredicate);
        }

        if (maxFee != null) {
            Predicate maxFeePredicate = builder.lessThanOrEqualTo(root.get("deliveryFee"), maxFee);
            predicates.add(maxFeePredicate);
        }

//        criteria.where(namePredicate, minFeePredicate, maxFeePredicate);
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();
    }
}
