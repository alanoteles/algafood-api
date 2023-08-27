package com.algaworks.algafoodapi.domain.exception;

public class EntityBeingUsedException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public EntityBeingUsedException(String message) {
        super(message);
    }
}
