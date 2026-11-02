package com.youcode.formation.controller;

import com.youcode.formation.model.Commande;
import com.youcode.formation.model.CommandeRequest;
import com.youcode.formation.service.CommandeCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Couche CONTROLLER : uniquement HTTP (entrée/sortie), aucune logique métier.
 * @RestController = @Controller + @ResponseBody : les retours sont écrits directement en JSON
 * (un @Controller classique renvoie un nom de vue). Le DispatcherServlet route chaque requête vers la bonne méthode.
 */
@RestController
@RequestMapping("/api/commandes")
public class CommandeController {
    private final CommandeCrudService service;

    public CommandeController(CommandeCrudService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les commandes")
    @ApiResponse(responseCode = "200", description = "Liste des commandes")
    public List<Commande> lister() {
        return service.lister();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une commande")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commande trouvée"),
            @ApiResponse(responseCode = "404", description = "Commande introuvable")})
    public Commande trouver(@PathVariable Long id) {
        return service.trouver(id);
    }

    @PostMapping
    @Operation(summary = "Créer une commande (remise de fidélité appliquée)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Commande créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou niveau inconnu"),
            @ApiResponse(responseCode = "409", description = "Référence déjà utilisée")})
    public ResponseEntity<Commande> creer(@Valid @RequestBody CommandeRequest request) {
        Commande creee = service.creer(request);
        return ResponseEntity.created(URI.create("/api/commandes/" + creee.id())).body(creee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une commande")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Supprimée"),
            @ApiResponse(responseCode = "404", description = "Commande introuvable")})
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
