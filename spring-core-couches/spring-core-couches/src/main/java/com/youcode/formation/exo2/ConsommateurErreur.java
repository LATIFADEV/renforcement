package com.youcode.formation.exo2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Étape 1 : lancer avec  --spring.profiles.active=erreur
 * Deux beans de type CommandeService, aucun choix indiqué ->
 * NoUniqueBeanDefinitionException : expected single matching bean but found 2:
 * commandeExpressService, commandeStandardService
 */
@Component
@Profile("erreur")
public class ConsommateurErreur {
    public ConsommateurErreur(CommandeService service) {
        System.out.println(service.traiter("ERR"));
    }
}
