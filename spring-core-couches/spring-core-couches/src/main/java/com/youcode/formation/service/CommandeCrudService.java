package com.youcode.formation.service;

import com.youcode.formation.model.Commande;
import com.youcode.formation.model.CommandeRequest;
import java.util.List;

/** Couche SERVICE : logique métier (interface d'abord). */
public interface CommandeCrudService {
    List<Commande> lister();

    Commande trouver(Long id);

    Commande creer(CommandeRequest request);

    void supprimer(Long id);
}
