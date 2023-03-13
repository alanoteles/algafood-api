package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Client;
import com.algaworks.algafoodapi.di.notification.Notificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ClientActivationService {

    @Qualifier("urgent")
    @Autowired
    private Notificator notificator;

    public void activate(Client client) {

        client.setActive();

        notificator.notificate(client, "Your register is active!");
    }

}