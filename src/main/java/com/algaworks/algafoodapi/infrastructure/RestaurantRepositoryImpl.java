package com.algaworks.algafoodapi.infrastructure;

import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee){
//        var jpql = "from Restaurant where name like :name " +
//                "and deliveryFee between :minFee and :maxFee";

        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 0 = 0 ");
        var queryParams = new HashMap<String, Object>();

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            queryParams.put("name", "%" + name + "%");
        }

        if (minFee != null) {
            jpql.append("and deliveryFee >= :minFee ");
            queryParams.put("minFee", minFee);
        }

        if (maxFee != null) {
            jpql.append("and deliveryFee <= :maxFee ");
            queryParams.put("maxFee", maxFee);
        }


        TypedQuery<Restaurant> query =  manager.createQuery(jpql.toString(), Restaurant.class);

        queryParams.forEach((key, value) -> query.setParameter(key, value));

        return query.getResultList();
    }
}
