package com.youcode.formation.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CommandeRequest(
        @NotBlank(message = "la référence est obligatoire") String reference,
        @NotBlank(message = "le client est obligatoire") String client,
        @Positive(message = "le montant doit être positif") double montant,
        @NotBlank(message = "le niveau de fidélité est obligatoire (SILVER, GOLD, PLATINUM)") String niveau) {
}
