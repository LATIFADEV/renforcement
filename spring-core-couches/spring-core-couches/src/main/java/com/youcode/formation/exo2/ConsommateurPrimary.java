package com.youcode.formation.exo2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/** Étape 3 : correction avec @Primary (voir config/PrimaryConfig). Lancer avec --spring.profiles.active=primary */
@Component
@Profile("primary")
public class ConsommateurPrimary {
    private final CommandeService service;

    public ConsommateurPrimary(CommandeService service) {
        this.service = service;
    }

    public String executer(String ref) {
        return service.traiter(ref);
    }
}
