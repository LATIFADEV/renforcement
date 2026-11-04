package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.CommandeDto;
import com.youcode.lambdas.dto.StatutStatDto;
import com.youcode.lambdas.entity.StatutCommande;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommandeService {
    List<CommandeDto> findAll();

    CommandeDto findById(Long id);

    /** EXO 7 : les 5 commandes les plus cheres, avec un STREAM (sorted + limit). */
    List<CommandeDto> top5CommandesStream();

    /** EXO 7 : meme resultat avec la methode derivee du repository. */
    List<CommandeDto> top5CommandesDerivee();

    /** EXO 8 : nombre de commandes par statut, avec un STREAM. */
    Map<StatutCommande, Long> nombreParStatutStream();

    /** EXO 8 : cumul des totaux par statut, avec un STREAM. */
    Map<StatutCommande, Double> cumulParStatutStream();

    /** EXO 8 : nombre et cumul par statut, avec une requete JPQL. */
    List<StatutStatDto> statsParStatutJpql();

    /** EXO 9 : le client qui a passe le plus de commandes (Optional : il peut ne pas y avoir de commande). */
    Optional<ClientCommandeCountDto> clientPlusActif();

    /** EXO 9 (variante) : commandes au statut CONFIRMED passees entre deux dates (incluses). */
    List<CommandeDto> confirmeesEntre(LocalDate debut, LocalDate fin);

    /** EXO 10 : noms des 5 clients ayant les commandes les plus cheres (sans doublon). */
    List<String> top5ClientsParCommandeChere();

    /** EXO 11 : commandes payees avec les 3 types de paiement, avec un STREAM. */
    List<CommandeDto> troisTypesPaiementStream();

    /** EXO 11 : meme resultat avec une requete JPQL. */
    List<CommandeDto> troisTypesPaiementJpql();
}
