package com.youcode.formation.exo2;

import org.springframework.stereotype.Service;

@Service
public class CommandeStandardService implements CommandeService {
    @Override
    public String traiter(String reference) {

        return "[STANDARD] commande " + reference + " livrée sous 5 jours";
    }
}
