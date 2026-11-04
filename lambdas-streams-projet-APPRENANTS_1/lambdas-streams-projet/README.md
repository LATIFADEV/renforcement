# lambdas-streams-projet (version apprenants)

Projet complet **Spring Boot 4.0.9** (Java 17+, Maven, JPA/Hibernate, H2 en mémoire, springdoc).
Architecture : `entity` → `repository` → `service` (interface + implémentation) → `controller`, avec `dto` et `mapper`.

## Lancer
IntelliJ : ouvrir le dossier (pom.xml), attendre l'import Maven, lancer `LambdasApplication`.
Swagger : http://localhost:8080/swagger-ui.html  ·  Tests : `mvn test`

## Travailler
Lire **EXERCICES.md** puis coder les méthodes marquées `TODO` (services, un peu de repository, et le runner pour les exos 1 et 6).
Fiche des méthodes : `docs/FICHE-STREAM-API.md`.

## Structure
```
com.youcode.lambdas
 |-- entity/ ........ Produit, Client, Commande, Paiement + enums
 |-- dto/ ........... records (ProduitDto, CommandeDto, PageDto...)
 |-- mapper/ ........ entité -> DTO
 |-- repository/ .... Spring Data (JPQL à ajouter pour les exos 7, 8, 11, 12, 13)
 |-- service/ ....... interfaces + Impl (les exercices)
 |-- controller/ .... un endpoint par exercice (Swagger)
 |-- exception/ ..... 404, 501 (exercice à faire), 400
 |-- config/ ........ DataInitializer (données fixes)
 `-- runner/ ........ ExercicesRunner (affiche chaque exercice, exos 1 et 6 à coder ici)
```
