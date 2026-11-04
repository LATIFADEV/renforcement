package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.PaiementDto;
import java.util.List;

public interface PaiementService {
    List<PaiementDto> findAll();

    /** EXO 3 : paiements de type CASH au statut ENCAISSE, tries par date decroissante. */
    List<PaiementDto> cashEncaisses();

    /** EXO 4 : paiements EN_ATTENTE dont le total est reduit de 10 % (dans le DTO), tries par date decroissante. */
    List<PaiementDto> enAttenteAvecRemise();

    /** EXO 5 : montant total de TVA collectee (paiements ENCAISSE), arrondi a 2 decimales. */
    double totalTvaCollectee();
}
