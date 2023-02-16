package com.algaworks.algafoodapi.di;

import com.algaworks.algafoodapi.di.notification.MailNotificator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {
    @Bean
    public MailNotificator mailNotificator() {
        MailNotificator notificator = new MailNotificator("smtp.algamail.com.br");

        return notificator;
    }
}
