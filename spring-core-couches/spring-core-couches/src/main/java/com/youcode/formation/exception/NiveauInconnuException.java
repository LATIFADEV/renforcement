package com.youcode.formation.exception;

import java.util.Collection;

public class NiveauInconnuException extends IllegalArgumentException {
    public NiveauInconnuException(String niveau, Collection<String> connus) {
        super("Niveau de fidélité inconnu : " + niveau + " (niveaux connus : " + connus + ")");
    }
}
