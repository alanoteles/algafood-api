package com.algaworks.algafoodapi.di.notification;

import com.algaworks.algafoodapi.di.model.Client;
import org.springframework.stereotype.Component;

@NotificatorType(UrgencyLevel.NON_URGENT)
@Component
public class SMSNotificator implements Notificator {

    @Override
    public void notificate(Client client, String msg) {

        System.out.printf("Notifying %s by SMS using the phone number %s: %s\n",
                client.getName(), client.getPhone(), msg);
    }
}
