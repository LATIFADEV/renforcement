package com.youcode.lambdas.controller;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.ClientDto;
import com.youcode.lambdas.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    public List<ClientDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/sans-commande")
    @Operation(summary = "EXO 12 : clients sans commande (JPQL)")
    public List<String> sansCommande() {
        return service.sansCommandeJpql();
    }

    @GetMapping("/sans-commande/stream")
    @Operation(summary = "EXO 12 : clients sans commande (stream)")
    public List<String> sansCommandeStream() {
        return service.sansCommandeStream();
    }

    @GetMapping("/nombre-commandes")
    @Operation(summary = "EXO 12 : nombre de commandes par client (JPQL)")
    public List<ClientCommandeCountDto> nombreCommandes() {
        return service.nombreCommandesParClientJpql();
    }
}
