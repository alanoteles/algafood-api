package com.algaworks.algafoodapi.di.notification;

import com.algaworks.algafoodapi.di.model.Client;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class MailNotificator implements Notificator {

    @Override
    public void notificate(Client client, String msg) {

        System.out.printf("Notifying %s using email %s: %s\n",
                client.getName(), client.getEmail(), msg);
    }
}
