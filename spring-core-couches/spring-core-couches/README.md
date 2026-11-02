# spring-core-couches

Projet Spring Boot (Java 17, Maven) qui réalise les 11 exercices de la journée :
**Matin : Spring Core (IoC, DI, beans)** et **Après-midi : architecture en couches et bons principes**.

## Lancer
IntelliJ IDEA : ouvrir le dossier (pom.xml), attendre l'import Maven, lancer `FormationApplication`.
Ou : `mvn spring-boot:run`  ·  Tests : `mvn test`
Swagger UI : http://localhost:8080/swagger-ui.html

## Où est chaque exercice ?

| # | Exercice | Où |
|---|----------|----|
| 1 | Projet Boot + pom.xml commenté | `pom.xml` |
| 2 | `CommandeService` à 2 implémentations, erreur, `@Qualifier`, `@Primary` | `exo2/`, `config/PrimaryConfig` |
| 3 | Bean hors du package de base (`@ComponentScan` / `@Import`) | `com.youcode.externe.AuditExterne`, `config/ScanExterneConfig` |
| 4 | Singleton vs prototype | `exo4/` (résultat dans la console) |
| 5 | Cycle de vie `@PostConstruct` / `@PreDestroy` | `exo5/ServiceCycleDeVie` |
| 6 | 3 types d'injection | `exo6/` |
| 7 | Remise fidélité : if/else puis `RemiseStrategy` | `remise/` |
| 8 | Découper le service fourre-tout | `exo8/` (`ServiceFourreTout` = avant) |
| 9 | Interface avant implémentation | tous les services (`XxxService` + `XxxServiceImpl`) |
| 10 | Test API (Swagger/Postman) + codes HTTP | `controller/`, `exception/`, `CommandeApiTest` |
| 11 | Schéma des couches + script de 2 min | `docs/schema-couches.md` |

## Rejouer l'exo 2 (l'erreur)
1. Run > Edit Configurations > Active profiles : `erreur` -> le démarrage échoue avec `NoUniqueBeanDefinitionException`.
2. Retirer le profil : `ConsommateurQualifier` corrige avec `@Qualifier`.
3. Profil `primary` : `ConsommateurPrimary` fonctionne grâce à `@Primary`.

## Exo 3 : prouver le problème
Commenter `@ComponentScan` dans `ScanExterneConfig` : `AuditExterne` n'est plus détecté et `DemoRunner` échoue
(`NoSuchBeanDefinitionException`). Remettre l'annotation (ou utiliser `@Import(AuditExterne.class)`).

## Exo 5 : voir `@PreDestroy`
Arrêter l'application proprement (bouton Stop d'IntelliJ ou Ctrl+C) : le message 5 s'affiche.

## Exo 7 : ajouter un niveau sans toucher au service
Créer `RemiseBronze implements RemiseStrategy` annotée `@Component` : le niveau BRONZE est pris en compte automatiquement.

## Tester l'API (exo 10)
| Requête | Code attendu |
|---------|--------------|
| `GET /api/commandes` | 200 |
| `POST /api/commandes` `{"reference":"C1","client":"Aya","montant":100,"niveau":"GOLD"}` | 201 |
| même POST une 2e fois | 409 |
| POST avec montant négatif ou niveau `DIAMANT` | 400 |
| `GET /api/commandes/999` | 404 |
| `GET /api/remises?niveau=GOLD&montant=200` | 200 |
| `DELETE /api/commandes/1` | 204 (puis 404) |
