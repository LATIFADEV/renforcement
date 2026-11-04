# Exercices — Jour 2 : Lambdas, Streams et requêtes

Le projet est **complet** (entités, repositories, DTO, mappers, services, controllers). Vous n'écrivez que les **méthodes marquées `TODO`** : chaque exercice indique où coder, quoi faire et quelles méthodes de l'API Stream utiliser.

## Comment travailler

1. Lancez `LambdasApplication` : la console affiche chaque exercice (**A FAIRE** tant qu'il n'est pas codé).
2. Codez un exercice, relancez `mvn test` : le test `exoN_...` correspondant doit passer au vert.
3. Vérifiez aussi dans Swagger : http://localhost:8080/swagger-ui.html (un endpoint par exercice ; 501 = exercice à faire).

Vous pouvez aussi suivre la fiche des méthodes : `docs/FICHE-STREAM-API.md`.

## Modèle de données

| Entité | Attributs |
|---|---|
| Produit | id, nom, prix, stock |
| Client | id, nom |
| Commande | id, reference, client, statut (PENDING, CONFIRMED, CANCELLED), total, dateCommande, paiements |
| Paiement | id, typePaiement (CASH, VIREMENT, CHEQUE), statut (ENCAISSE, EN_ATTENTE), total, montantTva, datePaiement, commande |

La TVA est de 20 % incluse dans le total (montantTva = total / 6). Les données sont créées au démarrage par `DataInitializer` (données fixes).

## Exercice 1 : Predicate, Function, Comparator à la main

**Où coder**

- `runner/ExercicesRunner` : méthode exo1(List<Produit>)

**À faire**

1. Écrire un Predicate<Produit> : stock > 50.
2. Écrire une Function<Produit, Double> : prix + 10 %.
3. Écrire un Comparator<Produit> : prix décroissant.
4. Les utiliser séparément : test() sur un produit, apply() sur un produit, sort() sur une liste.
5. Les utiliser ensemble dans un stream (filter, sorted, forEach).
6. Bonus : un Consumer, un Supplier et deux method references.

**Méthodes utiles** : Predicate.test, Function.apply, Comparator.comparingDouble(...).reversed(), list.sort(...), stream().filter().sorted().forEach(), Consumer.accept, Supplier.get, Produit::getNom, System.out::println

**Vérifier** : Console : les résultats séparés puis celui du stream s'affichent sous « EXO 1 ».

## Exercice 2 : Produits à fort stock, prix majoré

**Où coder**

- `service/ProduitServiceImpl` : stockEleveAvecMajoration()

**À faire**

1. Garder les produits dont le stock est > 50.
2. Les transformer en ProduitDto avec le prix +10 % (arrondi : Arrondi.deuxDecimales). Ne pas modifier l'entité.
3. Trier par prix décroissant.

**Méthodes utiles** : stream(), filter, map, sorted, Comparator.comparingDouble(ProduitDto::prix).reversed(), toList

**Vérifier** : Test exo2_… vert  |  GET /api/produits/stock-eleve  |  Casque en premier à 330.0

## Exercice 3 : Paiements CASH encaissés

**Où coder**

- `service/PaiementServiceImpl` : cashEncaisses()

**À faire**

1. Garder les paiements de type CASH au statut ENCAISSE.
2. Trier par date décroissante.
3. Convertir en DTO avec le mapper.

**Méthodes utiles** : filter (2 fois ou avec &&), sorted, Comparator.comparing(Paiement::getDatePaiement).reversed(), map(mapper::toDto), toList

**Vérifier** : Test exo3_… vert  |  GET /api/paiements/cash-encaisses  |  5 paiements, le plus récent en premier

## Exercice 4 : Paiements en attente avec remise

**Où coder**

- `service/PaiementServiceImpl` : enAttenteAvecRemise()

**À faire**

1. Garder les paiements EN_ATTENTE.
2. Retourner dans le DTO un total réduit de 10 % (utiliser mapper.toDto(paiement, nouveauTotal)). Ne pas modifier l'entité.
3. Trier par date décroissante.

**Méthodes utiles** : filter, sorted, map(p -> mapper.toDto(p, ...)), Arrondi.deuxDecimales, toList

**Vérifier** : Test exo4_… vert  |  GET /api/paiements/en-attente-remise  |  7 paiements, premier total = 31.5

## Exercice 5 : Total de TVA collectée

**Où coder**

- `service/PaiementServiceImpl` : totalTvaCollectee()

**À faire**

1. Ne garder que les paiements ENCAISSE.
2. Additionner leur montantTva.
3. Arrondir à 2 décimales.

**Méthodes utiles** : filter, mapToDouble(Paiement::getMontantTva), sum(), Arrondi.deuxDecimales

**Vérifier** : Test exo5_… vert  |  GET /api/paiements/tva-collectee  |  1711.67

## Exercice 6 : Recherche avec un critère en paramètre

**Où coder**

- `service/ProduitServiceImpl` : search(Predicate<Produit>)
- `runner/ExercicesRunner` : méthode exo6()

**À faire**

1. Dans search : filtrer les produits avec le critère reçu en paramètre.
2. Dans exo6 : appeler search avec 3 lambdas différentes : prix < 30, nom qui commence par S, stock == 0.
3. Expliquer en une phrase pourquoi on passe un Predicate plutôt que d'écrire 3 méthodes.

**Méthodes utiles** : filter(critere), map(mapper::toDto), toList, lambda p -> p.getPrix() < 30, String.startsWith

**Vérifier** : Test exo6_… vert  |  Console : les 3 résultats sous « EXO 6 »

## Exercice 7 : Les 5 commandes les plus chères

**Où coder**

- `service/CommandeServiceImpl` : top5CommandesStream() et top5CommandesDerivee()
- `repository/CommandeRepository` : méthode dérivée findTop5By... (à ajouter)

**À faire**

1. Version stream : trier par total décroissant puis garder 5.
2. Version méthode dérivée : ajouter dans CommandeRepository une méthode dont le nom suffit à Spring Data (Top5, OrderBy, Desc).
3. Vérifier que les 2 versions donnent le même résultat.

**Méthodes utiles** : sorted, Comparator.comparingDouble(Commande::getTotal).reversed(), limit(5), map, toList  |  Repository : findTop5ByOrderByTotalDesc

**Vérifier** : Test exo7_… vert  |  GET /api/commandes/top5 et /top5-derivee  |  3100, 2500, 1800, 1200, 950

## Exercice 8 : Commandes par statut

**Où coder**

- `service/CommandeServiceImpl` : nombreParStatutStream(), cumulParStatutStream(), statsParStatutJpql()
- `repository/CommandeRepository` : @Query JPQL avec GROUP BY (à ajouter)

**À faire**

1. Stream : nombre de commandes par statut.
2. Stream : cumul des totaux par statut.
3. JPQL : même résultat avec un GROUP BY, count et sum, retourné dans StatutStatDto.
4. Comparer les 3 résultats.

**Méthodes utiles** : collect, Collectors.groupingBy(Commande::getStatut, Collectors.counting()), Collectors.summingDouble  |  JPQL : select new ...StatutStatDto(c.statut, count(c), sum(c.total)) from Commande c group by c.statut

**Vérifier** : Test exo8_… vert  |  GET /api/commandes/par-statut/nombre, /cumul, /jpql  |  CONFIRMED 8, PENDING 4, CANCELLED 2

## Exercice 9 : Le client qui a passé le plus de commandes

**Où coder**

- `service/CommandeServiceImpl` : clientPlusActif() et confirmeesEntre(debut, fin)

**À faire**

1. Compter les commandes par nom de client.
2. Parcourir la Map et trouver l'entrée au compte maximal (le résultat est un Optional).
3. Variante : les commandes CONFIRMED passées entre deux dates (bornes incluses).

**Méthodes utiles** : groupingBy(c -> c.getClient().getNom(), counting()), entrySet().stream(), max(Map.Entry.comparingByValue()), Optional.map, LocalDate.isBefore / isAfter, filter

**Vérifier** : Test exo9_… vert  |  GET /api/commandes/client-plus-actif  |  Aya (4)  |  /confirmees?debut=2026-09-01&fin=2026-09-30

## Exercice 10 : Les 5 clients aux commandes les plus chères

**Où coder**

- `service/CommandeServiceImpl` : top5ClientsParCommandeChere()

**À faire**

1. Trier les commandes par total décroissant.
2. Prendre le nom du client de chaque commande.
3. Supprimer les doublons et garder 5 noms.

**Méthodes utiles** : sorted, map(c -> c.getClient().getNom()), distinct, limit(5), toList

**Vérifier** : Test exo10_… vert  |  GET /api/commandes/top5-clients  |  Hajar, Omar, Youssef, Aya, Salma

## Exercice 11 : Commandes payées avec les 3 types de paiement

**Où coder**

- `service/CommandeServiceImpl` : troisTypesPaiementStream() et troisTypesPaiementJpql()
- `repository/CommandeRepository` : @Query JPQL avec sous-requête (à ajouter)

**À faire**

1. Stream : pour chaque commande, collecter ses types de paiement dans un Set et vérifier qu'il contient CASH, VIREMENT et CHEQUE.
2. JPQL : compter les types distincts de paiement de la commande dans une sous-requête et garder ceux égaux à 3.
3. Vérifier que les 2 versions donnent les mêmes commandes.

**Méthodes utiles** : filter, getPaiements().stream().map(Paiement::getTypePaiement).collect(Collectors.toSet()), Set.containsAll(EnumSet.allOf(TypePaiement.class))  |  JPQL : count(distinct p.typePaiement) dans (select ... from Paiement p where p.commande = c)

**Vérifier** : Test exo11_… vert  |  GET /api/commandes/trois-types-paiement et /jpql  |  CMD-04 et CMD-10

## Exercice 12 : Clients sans commande, commandes par client

**Où coder**

- `service/ClientServiceImpl` : sansCommandeJpql(), sansCommandeStream(), nombreCommandesParClientJpql()
- `repository/ClientRepository` : @Query : clients sans commande (à ajouter)
- `repository/CommandeRepository` : @Query : nombre de commandes par client (à ajouter)

**À faire**

1. JPQL : les clients pour lesquels il n'existe aucune commande.
2. Stream : récupérer les ids des clients qui ont des commandes puis filtrer les clients absents de cet ensemble.
3. JPQL : nombre de commandes par client avec join et group by, retourné dans ClientCommandeCountDto.

**Méthodes utiles** : JPQL : not exists (select 1 from Commande o where o.client = c), join c.client, group by, count  |  Stream : map, collect(Collectors.toSet()), filter(... !ids.contains(...))

**Vérifier** : Test exo12_… vert  |  GET /api/clients/sans-commande  |  Karim, Nadia  |  /nombre-commandes

## Exercice 13 : Recherche de produits paginée et triée

**Où coder**

- `service/ProduitServiceImpl` : rechercher(nom, prixMin, prixMax, stockMin, pageable)
- `repository/ProduitRepository` : @Query paginée (à ajouter)

**À faire**

1. Chaque critère est facultatif (null = pas de filtre) : remplacer chaque null par une valeur neutre avant d'appeler le repository.
2. Le nom se cherche avec « contient », sans tenir compte des majuscules.
3. Le repository retourne une Page<Produit> : la convertir en PageDto<ProduitDto>.
4. Tester dans Swagger les cas de la colonne de droite.

**Méthodes utiles** : Optional.ofNullable(x).orElse(...), Page.map(mapper::toDto), PageDto.of(...)  |  JPQL : lower(p.nom) like lower(concat('%', :nom, '%')), p.prix between :min and :max, p.stock >= :stock  |  dernier paramètre : Pageable

**Vérifier** : Test exo13_… vert  |  GET /api/produits/recherche?nom=sou  |  ?stock=51&size=5&sort=prix&direction=desc  |  ?prixMin=100&prixMax=500
