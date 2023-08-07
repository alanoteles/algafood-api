package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateKitchenMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenJpa kitchenJpa = applicationContext.getBean(KitchenJpa.class);

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setName("Brazilian");

        kitchen = kitchenJpa.save(kitchen);

        System.out.printf("%d - %s\n", kitchen.getId(), kitchen.getName());

    }
}
