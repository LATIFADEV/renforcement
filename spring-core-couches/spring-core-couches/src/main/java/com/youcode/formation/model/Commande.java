package com.youcode.formation.model;

public record Commande(
        Long id,
        String reference,
        String client,
        double montantBrut,
        String niveau,
        double montantFinal) {
}
