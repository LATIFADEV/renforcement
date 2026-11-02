package com.youcode.formation.controller;

import com.youcode.formation.remise.RemiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remises")
public class RemiseController {
    private final RemiseService remiseService;

    public RemiseController(RemiseService remiseService) {
        this.remiseService = remiseService;
    }

    @GetMapping
    @Operation(summary = "Calculer le montant après remise de fidélité")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Montant calculé"),
            @ApiResponse(responseCode = "400", description = "Niveau inconnu ou montant négatif")})
    public Map<String, Object> calculer(@RequestParam String niveau, @RequestParam double montant) {
        return Map.of("niveau", niveau.toUpperCase(), "montant", montant,
                "montantFinal", remiseService.appliquer(niveau, montant));
    }
}
