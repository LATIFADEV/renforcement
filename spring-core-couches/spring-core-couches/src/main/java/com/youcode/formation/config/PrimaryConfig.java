package com.youcode.formation.config;

import com.youcode.formation.exo2.CommandeService;
import com.youcode.formation.exo2.CommandeStandardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Démonstration de @Primary (profil "primary") : en cas d'ambiguïté, ce bean est choisi par défaut.
 * Dans un vrai projet on poserait simplement @Primary sur la classe de l'implémentation par défaut.
 */
@Configuration
@Profile("primary")
public class PrimaryConfig {
    @Bean
    @Primary
    public CommandeService commandePrioritaire(CommandeStandardService standard) {

        return standard;
    }
}
