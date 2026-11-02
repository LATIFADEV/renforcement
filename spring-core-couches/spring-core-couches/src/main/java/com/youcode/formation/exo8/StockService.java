package com.youcode.formation.exo8;

/** EXO 8 - APRÈS : responsabilité unique = le stock. */
public interface StockService {
    void initialiser(String produit, int quantite);

    void reserver(String produit, int quantite);

    int disponible(String produit);
}
