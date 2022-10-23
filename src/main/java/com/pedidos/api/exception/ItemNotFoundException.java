package com.pedidos.api.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String s) {
        super(s);
    }
}
