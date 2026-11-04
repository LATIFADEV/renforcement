package com.youcode.lambdas.runner;

import com.youcode.lambdas.entity.Produit;
import com.youcode.lambdas.repository.ProduitRepository;
import com.youcode.lambdas.service.ClientService;
import com.youcode.lambdas.service.CommandeService;
import com.youcode.lambdas.service.PaiementService;
import com.youcode.lambdas.service.ProduitService;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Au demarrage, affiche le resultat de chaque exercice dans la console.
 * Un exercice pas encore code affiche "A FAIRE" (le projet demarre quand meme).
 * Les exercices 1 et 6 se codent DANS CETTE CLASSE (methodes exo1 et exo6).
 */
@Component
@Order(2)
public class ExercicesRunner implements CommandLineRunner {
    private final ProduitRepository produitRepository;
    private final ProduitService produitService;
    private final PaiementService paiementService;
    private final CommandeService commandeService;
    private final ClientService clientService;

    public ExercicesRunner(ProduitRepository produitRepository, ProduitService produitService,
                           PaiementService paiementService, CommandeService commandeService,
                           ClientService clientService) {
        this.produitRepository = produitRepository;
        this.produitService = produitService;
        this.paiementService = paiementService;
        this.commandeService = commandeService;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) {
        essayer("EXO 1  Predicate / Function / Comparator", () -> exo1(produitRepository.findAll()));
        essayer("EXO 2  stock > 50, prix +10 %, tri prix decroissant",
                () -> produitService.stockEleveAvecMajoration().forEach(System.out::println));
        essayer("EXO 3  paiements CASH encaisses",
                () -> paiementService.cashEncaisses().forEach(System.out::println));
        essayer("EXO 4  paiements en attente (-10 %)",
                () -> paiementService.enAttenteAvecRemise().forEach(System.out::println));
        essayer("EXO 5  TVA collectee", () -> System.out.println(paiementService.totalTvaCollectee() + " DH"));
        essayer("EXO 6  search(Predicate) avec 3 lambdas", this::exo6);
        essayer("EXO 7  5 commandes les plus cheres", () -> {
            commandeService.top5CommandesStream().forEach(System.out::println);
            System.out.println("-> methode derivee : " + commandeService.top5CommandesDerivee().size() + " resultats");
        });
        essayer("EXO 8  commandes par statut", () -> {
            System.out.println("nombre : " + commandeService.nombreParStatutStream());
            System.out.println("cumul  : " + commandeService.cumulParStatutStream());
            commandeService.statsParStatutJpql().forEach(s -> System.out.println("JPQL   : " + s));
        });
        essayer("EXO 9  client le plus actif + CONFIRMED en septembre", () -> {
            System.out.println(commandeService.clientPlusActif().map(Object::toString).orElse("aucun client"));
            commandeService.confirmeesEntre(LocalDate.parse("2026-09-01"), LocalDate.parse("2026-09-30"))
                    .forEach(System.out::println);
        });
        essayer("EXO 10 5 clients aux commandes les plus cheres",
                () -> System.out.println(commandeService.top5ClientsParCommandeChere()));
        essayer("EXO 11 commandes avec les 3 types de paiement", () -> {
            System.out.println("stream : " + commandeService.troisTypesPaiementStream().stream().map(c -> c.reference()).toList());
            System.out.println("JPQL   : " + commandeService.troisTypesPaiementJpql().stream().map(c -> c.reference()).toList());
        });
        essayer("EXO 12 clients sans commande + commandes par client", () -> {
            System.out.println("JPQL   : " + clientService.sansCommandeJpql());
            System.out.println("stream : " + clientService.sansCommandeStream());
            clientService.nombreCommandesParClientJpql().forEach(System.out::println);
        });
        System.out.println("\nEXO 13 : a tester dans Swagger -> GET /api/produits/recherche");
        System.out.println("Swagger UI : http://localhost:8080/swagger-ui.html\n");
    }

    /** EXO 1 : ecrire a la main un Predicate, une Function et un Comparator, puis les utiliser. */
    void exo1(List<Produit> produits) {
        // TODO exercice 1 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 1");
    }

    /** EXO 6 : appeler produitService.search(...) avec 3 lambdas differentes et afficher les resultats. */
    void exo6() {
        // Les 3 criteres : prix < 30  |  nom qui commence par "S"  |  stock == 0
        // TODO exercice 6 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 6");
    }

    private static void essayer(String titre, Runnable exercice) {
        System.out.println("\n===== " + titre + " =====");
        try {
            exercice.run();
        } catch (UnsupportedOperationException e) {
            System.out.println("  >>> A FAIRE (" + e.getMessage() + ")");
        }
    }
}
