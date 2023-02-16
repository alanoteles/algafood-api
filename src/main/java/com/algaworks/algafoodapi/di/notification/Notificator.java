package com.algaworks.algafoodapi.di.notification;

import com.algaworks.algafoodapi.di.model.Client;

public interface Notificator {
    void notificate(Client client, String msg);
}
