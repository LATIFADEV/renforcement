package com.youcode.formation.exo8;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/** Dépend d'ABSTRACTIONS (interfaces), pas d'implémentations -> Dependency Inversion Principle. */
@Service
public class PreparationCommandeServiceImpl implements PreparationCommandeService {
    private final StockService stockService;
    private final LigneCommandeService ligneService;
    private final AtomicLong sequence = new AtomicLong();

    public PreparationCommandeServiceImpl(StockService stockService, LigneCommandeService ligneService) {
        this.stockService = stockService;
        this.ligneService = ligneService;
    }

    @Override
    public long creer() {
        return sequence.incrementAndGet();
    }

    @Override
    public void ajouterProduit(long commandeId, String produit, int quantite) {
        stockService.reserver(produit, quantite);
        ligneService.ajouter(commandeId, produit, quantite);
    }
}
