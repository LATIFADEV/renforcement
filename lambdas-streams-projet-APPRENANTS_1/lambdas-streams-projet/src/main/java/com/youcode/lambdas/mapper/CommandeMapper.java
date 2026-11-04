package com.youcode.lambdas.mapper;

import com.youcode.lambdas.dto.CommandeDto;
import com.youcode.lambdas.entity.Commande;
import com.youcode.lambdas.util.Arrondi;
import org.springframework.stereotype.Component;

@Component
public class CommandeMapper {
    public CommandeDto toDto(Commande c) {
        return new CommandeDto(c.getId(), c.getReference(), c.getClient().getNom(), c.getStatut(),
                Arrondi.deuxDecimales(c.getTotal()), c.getDateCommande());
    }
}
