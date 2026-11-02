package com.youcode.formation.exo8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    private final Map<String, Integer> stock = new ConcurrentHashMap<>();

    @Override
    public void initialiser(String produit, int quantite) {
        stock.put(produit, quantite);
    }

    @Override
    public void reserver(String produit, int quantite) {
        int dispo = disponible(produit);
        if (dispo < quantite) {
            throw new IllegalStateException("Stock insuffisant pour " + produit);
        }
        stock.put(produit, dispo - quantite);
    }

    @Override
    public int disponible(String produit) {
        return stock.getOrDefault(produit, 0);
    }
}
