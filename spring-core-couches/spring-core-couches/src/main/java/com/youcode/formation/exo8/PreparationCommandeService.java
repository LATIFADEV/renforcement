package com.youcode.formation.exo8;

/** EXO 8 - APRÈS : responsabilité unique = orchestrer la préparation d'une commande. */
public interface PreparationCommandeService {
    long creer();

    void ajouterProduit(long commandeId, String produit, int quantite);
}
