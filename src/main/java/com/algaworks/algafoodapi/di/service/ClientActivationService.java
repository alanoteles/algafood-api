package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Client;
import com.algaworks.algafoodapi.di.notification.Notificator;
import com.algaworks.algafoodapi.di.notification.NotificatorType;
import com.algaworks.algafoodapi.di.notification.UrgencyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class ClientActivationService {

    @NotificatorType(UrgencyLevel.URGENT)
    @Autowired
    private Notificator notificator;

    @PostConstruct
    public void init(){
        System.out.println("INIT " + notificator);
    }

    @PreDestroy
    public void destroy(){

        System.out.println("DESTROY");
    }

    public void activate(Client client) {

        client.setActive();

        notificator.notificate(client, "Your register is active!");
    }

}