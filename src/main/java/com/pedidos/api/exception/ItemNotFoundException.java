package com.pedidos.api.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String item_não_encontrado) {
        super(item_não_encontrado);
    }
}
