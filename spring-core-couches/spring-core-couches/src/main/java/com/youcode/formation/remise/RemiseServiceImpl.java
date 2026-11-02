package com.youcode.formation.remise;

import com.youcode.formation.exception.NiveauInconnuException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Spring injecte TOUTES les implémentations de RemiseStrategy.
 * Pour ajouter un niveau : créer une classe @Component qui implémente RemiseStrategy.
 * CE SERVICE N'EST JAMAIS MODIFIÉ (Open/Closed). Exemple :
 *
 *   @Component public class RemiseBronze implements RemiseStrategy {
 *       public String niveau() { return "BRONZE"; }
 *       public double appliquer(double m) { return m * 0.98; }
 *   }
 */
@Service
public class RemiseServiceImpl implements RemiseService {
    private final Map<String, RemiseStrategy> strategies;

    public RemiseServiceImpl(List<RemiseStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(Collectors.toMap(s -> s.niveau().toUpperCase(), Function.identity()));
    }

    @Override
    public double appliquer(String niveau, double montant) {
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        RemiseStrategy strategy = strategies.get(niveau == null ? "" : niveau.toUpperCase());
        if (strategy == null) {
            throw new NiveauInconnuException(niveau, strategies.keySet());
        }
        return Math.round(strategy.appliquer(montant) * 100.0) / 100.0;
    }

    @Override
    public Set<String> niveauxConnus() {
        return strategies.keySet();
    }
}
