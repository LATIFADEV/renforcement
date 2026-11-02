package com.youcode.formation.exo5;

import com.youcode.formation.exo6.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * EXO 5 : cycle de vie. Ordre affiché :
 * 1 constructeur -> 2 injection (setter) -> 3 @PostConstruct -> 4 bean prêt -> 5 @PreDestroy (arrêt, Ctrl+C).
 */
@Component
public class ServiceCycleDeVie {
    public ServiceCycleDeVie() {
        System.out.println("[CYCLE] 1. Constructeur appelé (instanciation)");
    }

    @Autowired
    public void setDependance(MessageService message) {
        System.out.println("[CYCLE] 2. Injection des dépendances (setter)");
    }

    @PostConstruct
    public void init() {
        System.out.println("[CYCLE] 3. @PostConstruct : initialisation (bean prêt à l'emploi)");
    }

    @PreDestroy
    public void detruire() {
        System.out.println("[CYCLE] 5. @PreDestroy : nettoyage avant destruction du bean");
    }
}
