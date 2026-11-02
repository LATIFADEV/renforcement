package com.youcode.formation.exo4;

import java.util.UUID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** Scope prototype : une NOUVELLE instance à chaque injection / getBean. */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CompteurPrototype {
    private final String id = UUID.randomUUID().toString().substring(0, 8);

    public String getId() {
        return id;
    }
}
