package com.cafeteria.bedu.model.exceptions;

/**
 * Esta excepción es lanzada cuando el usuario ingresa una cantidad de dinero
 * menor al total de su orden.
 */
public class InsufficientCashException extends RuntimeException {

    public InsufficientCashException(String message) {
        super(message);
    }
}
