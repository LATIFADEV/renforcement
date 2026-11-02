package com.youcode.formation.runner;

import com.youcode.externe.AuditExterne;
import com.youcode.formation.exo2.ConsommateurPrimary;
import com.youcode.formation.exo2.ConsommateurQualifier;
import com.youcode.formation.exo4.CompteurPrototype;
import com.youcode.formation.exo4.CompteurSingleton;
import com.youcode.formation.exo6.GreeterChamp;
import com.youcode.formation.exo6.GreeterConstructeur;
import com.youcode.formation.exo6.GreeterSetter;
import com.youcode.formation.exo8.LigneCommandeService;
import com.youcode.formation.exo8.PreparationCommandeService;
import com.youcode.formation.exo8.StockService;
import com.youcode.formation.remise.RemiseIfElse;
import com.youcode.formation.remise.RemiseService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/** Affiche dans la console la preuve de chaque exercice (exo 2 à 8) au démarrage. */
@Component
public class DemoRunner implements CommandLineRunner {
    private final ApplicationContext ctx;
    private final ConsommateurQualifier consommateurQualifier;
    private final ObjectProvider<ConsommateurPrimary> consommateurPrimary;
    private final AuditExterne auditExterne;
    private final GreeterConstructeur greeterConstructeur;
    private final GreeterSetter greeterSetter;
    private final GreeterChamp greeterChamp;
    private final RemiseService remiseService;
    private final PreparationCommandeService preparation;
    private final StockService stockService;
    private final LigneCommandeService ligneService;

    public DemoRunner(ApplicationContext ctx, ConsommateurQualifier consommateurQualifier,
                      ObjectProvider<ConsommateurPrimary> consommateurPrimary, AuditExterne auditExterne,
                      GreeterConstructeur greeterConstructeur, GreeterSetter greeterSetter, GreeterChamp greeterChamp,
                      RemiseService remiseService, PreparationCommandeService preparation,
                      StockService stockService, LigneCommandeService ligneService) {
        this.ctx = ctx;
        this.consommateurQualifier = consommateurQualifier;
        this.consommateurPrimary = consommateurPrimary;
        this.auditExterne = auditExterne;
        this.greeterConstructeur = greeterConstructeur;
        this.greeterSetter = greeterSetter;
        this.greeterChamp = greeterChamp;
        this.remiseService = remiseService;
        this.preparation = preparation;
        this.stockService = stockService;
        this.ligneService = ligneService;
    }

    @Override
    public void run(String... args) {
        titre("EXO 2 - @Qualifier / @Primary");
        System.out.println(consommateurQualifier.executer("CMD-1") + "   (via @Qualifier)");
        consommateurPrimary.ifAvailable(c -> System.out.println(c.executer("CMD-2") + "   (via @Primary)"));

        titre("EXO 3 - Bean hors du package de base");
        System.out.println(auditExterne.info());

        titre("EXO 4 - Singleton vs prototype");
        CompteurSingleton s1 = ctx.getBean(CompteurSingleton.class);
        CompteurSingleton s2 = ctx.getBean(CompteurSingleton.class);
        CompteurPrototype p1 = ctx.getBean(CompteurPrototype.class);
        CompteurPrototype p2 = ctx.getBean(CompteurPrototype.class);
        System.out.println("Singleton : " + s1.getId() + " / " + s2.getId() + " -> même référence ? " + (s1 == s2));
        System.out.println("Prototype : " + p1.getId() + " / " + p2.getId() + " -> même référence ? " + (p1 == p2));

        titre("EXO 6 - Les 3 types d'injection");
        System.out.println(greeterConstructeur.saluer("Aya"));
        System.out.println(greeterSetter.saluer("Aya"));
        System.out.println(greeterChamp.saluer("Aya"));
        System.out.println("-> On garde le CONSTRUCTEUR (final, testable, dépendance obligatoire).");

        titre("EXO 7 - Remise fidélité (if/else puis Strategy)");
        System.out.println("if/else  GOLD 200 -> " + RemiseIfElse.appliquer("GOLD", 200));
        System.out.println("Strategy GOLD 200 -> " + remiseService.appliquer("GOLD", 200)
                + "   niveaux : " + remiseService.niveauxConnus());

        titre("EXO 8 - Service fourre-tout découpé");
        stockService.initialiser("clavier", 5);
        long id = preparation.creer();
        preparation.ajouterProduit(id, "clavier", 2);
        System.out.println("Lignes : " + ligneService.lignes(id) + " | stock restant clavier : "
                + stockService.disponible("clavier"));

        titre("API prête : http://localhost:8080/swagger-ui.html");
    }

    private static void titre(String t) {
        System.out.println("\n===== " + t + " =====");
    }
}
