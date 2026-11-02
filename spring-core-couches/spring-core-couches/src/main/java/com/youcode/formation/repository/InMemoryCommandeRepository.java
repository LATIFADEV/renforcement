package com.youcode.formation.repository;

import com.youcode.formation.model.Commande;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCommandeRepository implements CommandeRepository {
    private final Map<Long, Commande> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public List<Commande> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Commande> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public boolean existsByReference(String reference) {
        return data.values().stream().anyMatch(c -> c.reference().equalsIgnoreCase(reference));
    }

    @Override
    public Commande save(Commande c) {
        Commande saved = new Commande(sequence.incrementAndGet(), c.reference(), c.client(),
                c.montantBrut(), c.niveau(), c.montantFinal());
        data.put(saved.id(), saved);
        return saved;
    }

    @Override
    public boolean deleteById(Long id) {
        return data.remove(id) != null;
    }
}
