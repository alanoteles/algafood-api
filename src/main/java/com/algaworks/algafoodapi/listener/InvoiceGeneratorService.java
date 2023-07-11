package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.di.service.ClientActivatedEvent;
import org.springframework.context.event.EventListener;

public class InvoiceGeneratorService {

    @EventListener
    public void clientActivatedListener(ClientActivatedEvent event) {
        System.out.println("Generating invoice to " + event.getClient().getName());
    }
}
