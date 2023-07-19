package com.algaworks.algafoodapi.di.notification;

import com.algaworks.algafoodapi.di.model.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@NotificatorType(UrgencyLevel.URGENT)
@Component
public class MailNotificator implements Notificator {

    @Value("${mail.notificator.server-host}")
    private String host;

    @Value("${mail.notificator.server-port}")
    private Integer port;

    @Override
    public void notificate(Client client, String msg) {
        System.out.println("Host:" + host);
        System.out.println("Port:" + port);

        System.out.printf("Notifying %s using email %s: %s\n",
                client.getName(), client.getEmail(), msg);
    }
}
