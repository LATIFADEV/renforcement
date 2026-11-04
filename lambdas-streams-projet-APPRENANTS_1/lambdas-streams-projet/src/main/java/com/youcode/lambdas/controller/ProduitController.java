package com.youcode.lambdas.controller;

import com.youcode.lambdas.dto.PageDto;
import com.youcode.lambdas.dto.ProduitDto;
import com.youcode.lambdas.dto.ProduitRequest;
import com.youcode.lambdas.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private static final Set<String> CHAMPS_TRI = Set.of("id", "nom", "prix", "stock");

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister tous les produits")
    public List<ProduitDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un produit (404 si inconnu)")
    public ProduitDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Creer un produit")
    public ResponseEntity<ProduitDto> create(@RequestBody ProduitRequest request) {
        ProduitDto cree = service.create(request);
        return ResponseEntity.created(URI.create("/api/produits/" + cree.id())).body(cree);
    }

    @GetMapping("/stock-eleve")
    @Operation(summary = "EXO 2 : stock > 50, prix +10 %, tri prix decroissant")
    public List<ProduitDto> stockEleve() {
        return service.stockEleveAvecMajoration();
    }

    @GetMapping("/recherche")
    @Operation(summary = "EXO 13 : recherche (nom, prixMin, prixMax, stock), paginee et triee")
    public PageDto<ProduitDto> rechercher(@RequestParam(required = false) String nom,
                                          @RequestParam(required = false) Double prixMin,
                                          @RequestParam(required = false) Double prixMax,
                                          @RequestParam(required = false) Integer stock,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size,
                                          @RequestParam(defaultValue = "nom") String sort,
                                          @RequestParam(defaultValue = "asc") String direction) {
        if (!CHAMPS_TRI.contains(sort)) {
            throw new IllegalArgumentException("Champ de tri inconnu : " + sort + " (autorises : " + CHAMPS_TRI + ")");
        }
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        return service.rechercher(nom, prixMin, prixMax, stock, pageable);
    }
}
