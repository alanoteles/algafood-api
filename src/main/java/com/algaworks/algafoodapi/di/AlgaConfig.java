package com.algaworks.algafoodapi.di;

import com.algaworks.algafoodapi.di.notification.MailNotificator;
import com.algaworks.algafoodapi.di.service.ClientActivationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AlgaConfig {

    @Bean
    public MailNotificator mailNotificator() {
        MailNotificator notificator = new MailNotificator("smtp.algamail.com.br");
        notificator.setUpperCase(false);

        return notificator;
    }

    @Bean
    public ClientActivationService clientActivationService() {

        return new ClientActivationService(mailNotificator());
    }
}
