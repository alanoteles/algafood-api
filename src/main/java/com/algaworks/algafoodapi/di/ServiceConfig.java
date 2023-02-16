package com.algaworks.algafoodapi.di;

import com.algaworks.algafoodapi.di.notification.Notificator;
import com.algaworks.algafoodapi.di.service.ClientActivationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ClientActivationService clientActivationService(Notificator notificator) {

        return new ClientActivationService(notificator);
    }
}
