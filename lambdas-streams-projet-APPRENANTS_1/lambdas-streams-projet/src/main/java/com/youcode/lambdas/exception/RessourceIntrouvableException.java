package com.youcode.lambdas.exception;

public class RessourceIntrouvableException extends RuntimeException {
    public RessourceIntrouvableException(String type, Object id) {
        super(type + " introuvable : id=" + id);
    }
}
