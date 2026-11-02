package com.youcode.formation.exo4;

import java.util.UUID;
import org.springframework.stereotype.Component;

/** Scope par défaut = singleton : une seule instance par ApplicationContext. */
@Component
public class CompteurSingleton {
    private final String id = UUID.randomUUID().toString().substring(0, 8);

    public String getId() {
        return id;
    }
}
