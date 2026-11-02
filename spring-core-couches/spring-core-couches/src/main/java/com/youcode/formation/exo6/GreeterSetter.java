package com.youcode.formation.exo6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Injection par SETTER : pour les dépendances optionnelles ; l'objet peut exister sans sa dépendance. */
@Component
public class GreeterSetter {
    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String saluer(String nom) {
        return "(setter) " + messageService.message(nom);
    }
}
