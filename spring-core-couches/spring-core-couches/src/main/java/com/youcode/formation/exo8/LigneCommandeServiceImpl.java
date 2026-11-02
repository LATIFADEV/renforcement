package com.youcode.formation.exo8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class LigneCommandeServiceImpl implements LigneCommandeService {
    private final Map<Long, List<String>> lignes = new ConcurrentHashMap<>();

    @Override
    public void ajouter(long commandeId, String produit, int quantite) {
        lignes.computeIfAbsent(commandeId, id -> new ArrayList<>()).add(produit + " x" + quantite);
    }

    @Override
    public List<String> lignes(long commandeId) {
        return List.copyOf(lignes.getOrDefault(commandeId, List.of()));
    }
}
