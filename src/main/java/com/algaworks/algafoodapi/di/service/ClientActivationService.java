package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Client;
import com.algaworks.algafoodapi.di.notification.Notificator;
import org.springframework.stereotype.Component;


public class ClientActivationService {

    private Notificator notificator;

    public ClientActivationService(Notificator notificator) {
        this.notificator = notificator;
    }

    public void activate(Client client) {

        client.setActive();
        notificator.notificate(client, "Your register is active!");
    }
}