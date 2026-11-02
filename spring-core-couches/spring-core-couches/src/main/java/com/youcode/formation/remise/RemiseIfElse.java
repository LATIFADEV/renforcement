package com.youcode.formation.remise;

/**
 * EXO 7 - VERSION 1 (à ne pas garder) : if/else.
 * Ajouter un niveau (ex. BRONZE) oblige à MODIFIER cette méthode -> viole Open/Closed.
 */
public final class RemiseIfElse {
    private RemiseIfElse() {
    }

    public static double appliquer(String niveau, double montant) {
        if ("SILVER".equals(niveau)) {
            return montant * 0.95;
        } else if ("GOLD".equals(niveau)) {
            return montant * 0.90;
        } else if ("PLATINUM".equals(niveau)) {
            return montant * 0.85;
        }
        throw new IllegalArgumentException("Niveau inconnu : " + niveau);
    }
}
