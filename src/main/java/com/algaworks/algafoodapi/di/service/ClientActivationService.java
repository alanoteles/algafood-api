package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Client;
import com.algaworks.algafoodapi.di.notification.Notificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientActivationService {

    @Autowired
    private List<Notificator> notificators;

    public void activate(Client client) {

        client.setActive();

        for (Notificator notificator : notificators) {
            notificator.notificate(client, "Your register is active!");
        }
    }


//    public void setNotificator(Notificator notificator) {
//        this.notificator = notificator;
//    }
}