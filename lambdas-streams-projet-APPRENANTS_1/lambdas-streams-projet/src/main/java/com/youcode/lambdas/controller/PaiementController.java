package com.youcode.lambdas.controller;

import com.youcode.lambdas.dto.PaiementDto;
import com.youcode.lambdas.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
    private final PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    public List<PaiementDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/cash-encaisses")
    @Operation(summary = "EXO 3 : paiements CASH encaisses, date decroissante")
    public List<PaiementDto> cashEncaisses() {
        return service.cashEncaisses();
    }

    @GetMapping("/en-attente-remise")
    @Operation(summary = "EXO 4 : paiements EN_ATTENTE avec 10 % de remise")
    public List<PaiementDto> enAttenteAvecRemise() {
        return service.enAttenteAvecRemise();
    }

    @GetMapping("/tva-collectee")
    @Operation(summary = "EXO 5 : montant total de TVA collectee")
    public Map<String, Double> tva() {
        return Map.of("tvaCollectee", service.totalTvaCollectee());
    }
}
