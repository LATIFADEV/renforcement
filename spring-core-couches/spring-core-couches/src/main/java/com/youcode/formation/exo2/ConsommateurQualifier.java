package com.youcode.formation.exo2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/** Étape 2 : correction avec @Qualifier -> on choisit explicitement le bean par son nom. */
@Component
public class ConsommateurQualifier {
    private final CommandeService service;

    public ConsommateurQualifier(@Qualifier("commandeExpressService") CommandeService service) {
        this.service = service;
    }

    public String executer(String ref) {
        return service.traiter(ref);
    }
}
