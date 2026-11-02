package com.youcode.formation.remise;

/** EXO 7 - VERSION 2 : une stratégie par niveau de fidélité (pattern Strategy). */
public interface RemiseStrategy {
    /** Code du niveau géré (SILVER, GOLD...). */
    String niveau();

    double appliquer(double montant);
}
