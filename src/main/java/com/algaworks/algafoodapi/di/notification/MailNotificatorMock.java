package com.algaworks.algafoodapi.di.notification;

import com.algaworks.algafoodapi.di.model.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@NotificatorType(UrgencyLevel.URGENT)
@Component
public class MailNotificatorMock implements Notificator {

    public MailNotificatorMock(){
        System.out.println("MailNotificator MOCK");
    }
    @Override
    public void notificate(Client client, String msg) {

        System.out.printf("MOCK: Notification would be send to %s using email %s: %s\n",
                client.getName(), client.getEmail(), msg);
    }
}
