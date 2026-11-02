package com.youcode.formation.remise;

import java.util.Set;

/** Contrat du service de remise (interface d'abord, implémentation ensuite). */
public interface RemiseService {
    double appliquer(String niveau, double montant);

    Set<String> niveauxConnus();
}
