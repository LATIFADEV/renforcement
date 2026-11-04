package com.youcode.lambdas.repository;

import com.youcode.lambdas.entity.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // EXO 12 : ajouter ici une requete JPQL qui retourne les clients sans aucune commande.
    // TODO exercice 12 : ajouter ici la methode du repository
}
