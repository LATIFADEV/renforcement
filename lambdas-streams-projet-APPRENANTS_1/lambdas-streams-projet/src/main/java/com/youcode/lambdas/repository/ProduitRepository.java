package com.youcode.lambdas.repository;

import com.youcode.lambdas.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    // EXO 13 : ajouter ici une methode paginee avec @Query (nom contient, prix entre min et max, stock >= minimum).
    // Sa signature finit par un parametre Pageable et retourne Page<Produit>.
    // TODO exercice 13 : ajouter ici la methode du repository
}
