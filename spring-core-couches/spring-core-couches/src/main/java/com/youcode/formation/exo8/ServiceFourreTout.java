package com.youcode.formation.exo8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EXO 8 - AVANT : service "fourre-tout" (commandes + lignes + stock).
 * Trop de raisons de changer -> viole Single Responsibility. N'est PAS un bean (conservé pour comparaison).
 */
public class ServiceFourreTout {
    private final Map<Long, List<String>> lignes = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();
    private long sequence = 0;

    public long creerCommande() {
        lignes.put(++sequence, new ArrayList<>());
        return sequence;
    }

    public void ajouterLigne(long commandeId, String produit, int quantite) {
        int dispo = stock.getOrDefault(produit, 0);
        if (dispo < quantite) {
            throw new IllegalStateException("Stock insuffisant pour " + produit);
        }
        stock.put(produit, dispo - quantite);
        lignes.get(commandeId).add(produit + " x" + quantite);
    }
}
