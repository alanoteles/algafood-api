package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Client;

public class ClientActivatedEvent
{
    private Client client;

    public ClientActivatedEvent(Client client) {
        super();
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
