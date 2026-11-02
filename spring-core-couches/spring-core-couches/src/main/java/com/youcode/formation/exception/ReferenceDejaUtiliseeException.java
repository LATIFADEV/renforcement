package com.youcode.formation.exception;

public class ReferenceDejaUtiliseeException extends RuntimeException {
    public ReferenceDejaUtiliseeException(String reference) {
        super("La référence existe déjà : " + reference);
    }
}
