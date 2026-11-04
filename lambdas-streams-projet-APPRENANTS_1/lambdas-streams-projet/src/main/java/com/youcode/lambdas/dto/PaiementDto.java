package com.youcode.lambdas.dto;

import com.youcode.lambdas.entity.StatutPaiement;
import com.youcode.lambdas.entity.TypePaiement;
import java.time.LocalDate;

public record PaiementDto(Long id, TypePaiement type, StatutPaiement statut, double total, LocalDate date) {
}
