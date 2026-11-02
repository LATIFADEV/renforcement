package com.youcode.formation.repository;

import com.youcode.formation.model.Commande;
import java.util.List;
import java.util.Optional;

/** Couche REPOSITORY : accès aux données (interface d'abord). */
public interface CommandeRepository {
    List<Commande> findAll();

    Optional<Commande> findById(Long id);

    boolean existsByReference(String reference);

    Commande save(Commande commande);

    boolean deleteById(Long id);
}
