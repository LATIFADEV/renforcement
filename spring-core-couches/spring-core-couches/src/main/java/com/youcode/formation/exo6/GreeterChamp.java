package com.youcode.formation.exo6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Injection par CHAMP : concise mais déconseillée (non final, impossible à instancier sans Spring/réflexion). */
@Component
public class GreeterChamp {
    @Autowired
    private MessageService messageService;

    public String saluer(String nom) {
        return "(champ) " + messageService.message(nom);
    }
}
