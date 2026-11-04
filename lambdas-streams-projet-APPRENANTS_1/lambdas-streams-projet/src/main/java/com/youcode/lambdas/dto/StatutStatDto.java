package com.youcode.lambdas.dto;

import com.youcode.lambdas.entity.StatutCommande;

/** Nombre de commandes et cumul des totaux pour un statut (Long / Double : types renvoyes par count() et sum()). */
public record StatutStatDto(StatutCommande statut, Long nombre, Double total) {
}
