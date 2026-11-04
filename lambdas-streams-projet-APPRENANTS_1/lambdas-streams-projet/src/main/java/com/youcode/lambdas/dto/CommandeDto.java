package com.youcode.lambdas.dto;

import com.youcode.lambdas.entity.StatutCommande;
import java.time.LocalDate;

public record CommandeDto(Long id, String reference, String client, StatutCommande statut, double total, LocalDate date) {
}
