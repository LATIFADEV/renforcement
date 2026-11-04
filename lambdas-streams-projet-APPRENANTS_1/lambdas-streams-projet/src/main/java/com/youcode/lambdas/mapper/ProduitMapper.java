package com.youcode.lambdas.mapper;

import com.youcode.lambdas.dto.ProduitDto;
import com.youcode.lambdas.dto.ProduitRequest;
import com.youcode.lambdas.entity.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {
    public ProduitDto toDto(Produit p) {
        return new ProduitDto(p.getId(), p.getNom(), p.getPrix(), p.getStock());
    }

    public Produit toEntity(ProduitRequest r) {
        return new Produit(r.nom(), r.prix(), r.stock());
    }
}
