package com.youcode.lambdas.mapper;

import com.youcode.lambdas.dto.PaiementDto;
import com.youcode.lambdas.entity.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {
    public PaiementDto toDto(Paiement p) {
        return toDto(p, p.getTotal());
    }

    /** Variante : permet d'indiquer un autre total (par exemple remise) SANS modifier l'entite. */
    public PaiementDto toDto(Paiement p, double total) {
        return new PaiementDto(p.getId(), p.getTypePaiement(), p.getStatut(), total, p.getDatePaiement());
    }
}
