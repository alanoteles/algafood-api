package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ClientActivationService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void activate(Client client) {

        client.setActive();
        eventPublisher.publishEvent(new ClientActivatedEvent(client));

    }

}