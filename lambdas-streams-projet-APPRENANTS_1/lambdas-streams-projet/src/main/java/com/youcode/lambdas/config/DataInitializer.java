package com.youcode.lambdas.config;

import com.youcode.lambdas.entity.Client;
import com.youcode.lambdas.entity.Commande;
import com.youcode.lambdas.entity.Paiement;
import com.youcode.lambdas.entity.Produit;
import com.youcode.lambdas.entity.StatutCommande;
import com.youcode.lambdas.entity.StatutPaiement;
import com.youcode.lambdas.entity.TypePaiement;
import com.youcode.lambdas.repository.ClientRepository;
import com.youcode.lambdas.repository.CommandeRepository;
import com.youcode.lambdas.repository.ProduitRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Jeu de donnees FIXE (pas d'aleatoire) : les resultats attendus des exercices sont donc toujours les memes. */
@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    private final ProduitRepository produits;
    private final ClientRepository clients;
    private final CommandeRepository commandes;

    public DataInitializer(ProduitRepository produits, ClientRepository clients, CommandeRepository commandes) {
        this.produits = produits;
        this.clients = clients;
        this.commandes = commandes;
    }

    @Override
    public void run(String... args) {
        produits.saveAll(List.of(
                new Produit("Stylo", 2.5, 200), new Produit("Cahier", 12, 80), new Produit("Souris", 150, 60),
                new Produit("Clavier", 220, 30), new Produit("Ecran", 1800, 12), new Produit("Sac", 90, 55),
                new Produit("Stabilo", 6, 0), new Produit("Casque", 300, 51), new Produit("Cable USB", 25, 120),
                new Produit("Imprimante", 900, 5), new Produit("Agrafeuse", 35, 75), new Produit("Souris Gamer", 450, 20)));

        Client aya = clients.save(new Client("Aya"));
        Client omar = clients.save(new Client("Omar"));
        Client salma = clients.save(new Client("Salma"));
        Client youssef = clients.save(new Client("Youssef"));
        Client hajar = clients.save(new Client("Hajar"));
        clients.save(new Client("Karim"));   // sans commande
        clients.save(new Client("Nadia"));   // sans commande

        TypePaiement cash = TypePaiement.CASH, vir = TypePaiement.VIREMENT, chq = TypePaiement.CHEQUE;
        StatutPaiement ok = StatutPaiement.ENCAISSE, att = StatutPaiement.EN_ATTENTE;
        StatutCommande conf = StatutCommande.CONFIRMED, pend = StatutCommande.PENDING, ann = StatutCommande.CANCELLED;

        cmd("CMD-01", aya, conf, 1200, "2026-09-02", p(cash, ok, 500, "2026-09-02"), p(vir, ok, 700, "2026-09-03"));
        cmd("CMD-02", aya, pend, 300, "2026-09-15", p(cash, att, 300, "2026-09-15"));
        cmd("CMD-03", aya, conf, 80, "2026-10-01", p(chq, ok, 80, "2026-10-01"));
        cmd("CMD-04", omar, conf, 2500, "2026-09-10", p(cash, ok, 1000, "2026-09-10"),
                p(vir, ok, 1000, "2026-09-11"), p(chq, ok, 500, "2026-09-12"));
        cmd("CMD-05", omar, ann, 150, "2026-09-20", p(vir, att, 150, "2026-09-20"));
        cmd("CMD-06", salma, conf, 640, "2026-09-25", p(cash, ok, 640, "2026-09-25"));
        cmd("CMD-07", salma, pend, 90, "2026-10-05", p(cash, att, 90, "2026-10-05"));
        cmd("CMD-08", youssef, conf, 1800, "2026-10-08", p(vir, ok, 1800, "2026-10-08"));
        cmd("CMD-09", youssef, conf, 420, "2026-10-12", p(chq, att, 420, "2026-10-12"));
        cmd("CMD-10", hajar, conf, 3100, "2026-10-15", p(cash, ok, 1100, "2026-10-15"),
                p(vir, ok, 1000, "2026-10-16"), p(chq, ok, 1000, "2026-10-17"));
        cmd("CMD-11", hajar, pend, 60, "2026-10-20", p(cash, att, 60, "2026-10-20"));
        cmd("CMD-12", aya, conf, 950, "2026-10-22", p(cash, ok, 950, "2026-10-22"));
        cmd("CMD-13", omar, pend, 275, "2026-10-25", p(chq, att, 275, "2026-10-25"));
        cmd("CMD-14", youssef, ann, 35, "2026-10-27", p(cash, att, 35, "2026-10-27"));
    }

    private static Paiement p(TypePaiement t, StatutPaiement s, double total, String date) {
        return new Paiement(t, s, total, LocalDate.parse(date));
    }

    private void cmd(String ref, Client client, StatutCommande statut, double total, String date, Paiement... paiements) {
        Commande c = new Commande(ref, client, statut, total, LocalDate.parse(date));
        for (Paiement paiement : paiements) {
            c.ajouterPaiement(paiement);
        }
        commandes.save(c);
    }
}
