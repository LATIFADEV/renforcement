package com.youcode.lambdas.repository;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.StatutStatDto;
import com.youcode.lambdas.entity.Commande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // EXO 7 : ajouter une methode DERIVEE (pas de code, le nom de la methode suffit) : les 5 commandes au total le plus eleve.
    // TODO exercice 7 : ajouter ici la methode du repository

    // EXO 8 : ajouter une requete JPQL (GROUP BY) qui retourne List<StatutStatDto> : statut, nombre, cumul des totaux.
    // TODO exercice 8 : ajouter ici la methode du repository

    // EXO 11 : ajouter une requete JPQL : commandes payees avec les 3 types de paiement (sous-requete avec count(distinct ...)).
    // TODO exercice 11 : ajouter ici la methode du repository

    // EXO 12 : ajouter une requete JPQL (join + group by) qui retourne List<ClientCommandeCountDto> : nom du client, nombre de commandes.
    // TODO exercice 12 : ajouter ici la methode du repository
}
