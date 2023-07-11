package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.di.notification.Notificator;
import com.algaworks.algafoodapi.di.notification.NotificatorType;
import com.algaworks.algafoodapi.di.notification.UrgencyLevel;
import com.algaworks.algafoodapi.di.service.ClientActivatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @NotificatorType(UrgencyLevel.URGENT)
    @Autowired
    private Notificator notificator;

    @EventListener
    public void clientActivatedListener(ClientActivatedEvent event) {
        System.out.println("Client " + event.getClient().getName() + " now is active");
        notificator.notificate(event.getClient(), "Your record is active!");
    }
}
