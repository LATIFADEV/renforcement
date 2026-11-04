package com.youcode.lambdas.controller;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.CommandeDto;
import com.youcode.lambdas.dto.StatutStatDto;
import com.youcode.lambdas.entity.StatutCommande;
import com.youcode.lambdas.service.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {
    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister toutes les commandes")
    public List<CommandeDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une commande (404 si inconnue)")
    public CommandeDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/top5")
    @Operation(summary = "EXO 7 : les 5 commandes les plus cheres (stream)")
    public List<CommandeDto> top5() {
        return service.top5CommandesStream();
    }

    @GetMapping("/top5-derivee")
    @Operation(summary = "EXO 7 : les 5 commandes les plus cheres (methode derivee)")
    public List<CommandeDto> top5Derivee() {
        return service.top5CommandesDerivee();
    }

    @GetMapping("/par-statut/nombre")
    @Operation(summary = "EXO 8 : nombre de commandes par statut (stream)")
    public Map<StatutCommande, Long> nombreParStatut() {
        return service.nombreParStatutStream();
    }

    @GetMapping("/par-statut/cumul")
    @Operation(summary = "EXO 8 : cumul des totaux par statut (stream)")
    public Map<StatutCommande, Double> cumulParStatut() {
        return service.cumulParStatutStream();
    }

    @GetMapping("/par-statut/jpql")
    @Operation(summary = "EXO 8 : nombre et cumul par statut (JPQL)")
    public List<StatutStatDto> statsJpql() {
        return service.statsParStatutJpql();
    }

    @GetMapping("/client-plus-actif")
    @Operation(summary = "EXO 9 : client qui a passe le plus de commandes")
    public ResponseEntity<ClientCommandeCountDto> clientPlusActif() {
        return service.clientPlusActif().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/confirmees")
    @Operation(summary = "EXO 9 (variante) : commandes CONFIRMED entre deux dates (AAAA-MM-JJ)")
    public List<CommandeDto> confirmees(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return service.confirmeesEntre(debut, fin);
    }

    @GetMapping("/top5-clients")
    @Operation(summary = "EXO 10 : 5 clients aux commandes les plus cheres")
    public List<String> top5Clients() {
        return service.top5ClientsParCommandeChere();
    }

    @GetMapping("/trois-types-paiement")
    @Operation(summary = "EXO 11 : commandes payees avec les 3 types (stream)")
    public List<CommandeDto> troisTypesStream() {
        return service.troisTypesPaiementStream();
    }

    @GetMapping("/trois-types-paiement/jpql")
    @Operation(summary = "EXO 11 : commandes payees avec les 3 types (JPQL)")
    public List<CommandeDto> troisTypesJpql() {
        return service.troisTypesPaiementJpql();
    }
}
