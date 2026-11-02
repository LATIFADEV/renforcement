package com.youcode.formation.exo6;

import org.springframework.stereotype.Component;

/**
 * Injection par CONSTRUCTEUR (celle qu'on garde) :
 * dépendance obligatoire et final (immuable), objet toujours valide,
 * testable sans Spring (new GreeterConstructeur(mock)), pas besoin d'@Autowired.
 */
@Component
public class GreeterConstructeur {
    private final MessageService messageService;

    public GreeterConstructeur(MessageService messageService) {
        this.messageService = messageService;
    }

    public String saluer(String nom) {
        return "(constructeur) " + messageService.message(nom);
    }
}
