package com.youcode.formation.exception;

public class CommandeIntrouvableException extends RuntimeException {
    public CommandeIntrouvableException(Long id) {
        super("Commande introuvable : id=" + id);
    }
}
