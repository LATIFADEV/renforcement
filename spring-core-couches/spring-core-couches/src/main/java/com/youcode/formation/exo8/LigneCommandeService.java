package com.youcode.formation.exo8;

import java.util.List;

/** EXO 8 - APRÈS : responsabilité unique = les lignes de commande. */
public interface LigneCommandeService {
    void ajouter(long commandeId, String produit, int quantite);

    List<String> lignes(long commandeId);
}
