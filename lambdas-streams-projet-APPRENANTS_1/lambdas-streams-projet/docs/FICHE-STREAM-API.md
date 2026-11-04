# Fiche : méthodes de l'API Stream et outils utiles

Aucune solution ici : seulement les méthodes à connaître pour résoudre les exercices.

## Interfaces fonctionnelles (java.util.function)

| Méthode | À quoi ça sert |
|---|---|
| `Predicate<T>` | test(t) -> boolean  |  and(...), or(...), negate() |
| `Function<T,R>` | apply(t) -> R  |  andThen(...), compose(...) |
| `Consumer<T>` | accept(t) -> void (pour forEach) |
| `Supplier<T>` | get() -> T (ne prend rien) |
| `Comparator<T>` | compare(a, b)  |  Comparator.comparing / comparingDouble / comparingInt, reversed(), thenComparing(...) |
| `@FunctionalInterface` | une seule méthode abstraite : utilisable avec une lambda |
| `Method reference` | Classe::méthode statique | objet::méthode | Classe::méthodeInstance (Produit::getNom) | Classe::new |

## Créer un stream

| Méthode | À quoi ça sert |
|---|---|
| `liste.stream()` | stream à partir d'une List ou d'un Set |
| `Stream.of(a, b, c)` | stream à partir de valeurs |
| `map.entrySet().stream()` | parcourir une Map (getKey(), getValue()) |

## Opérations intermédiaires (retournent un stream)

| Méthode | À quoi ça sert |
|---|---|
| `filter(predicate)` | garde les éléments qui vérifient la condition |
| `map(function)` | transforme chaque élément |
| `mapToDouble / mapToInt` | transforme en nombres (pour sum, average, max) |
| `sorted() / sorted(comparator)` | trie |
| `distinct()` | supprime les doublons |
| `limit(n) / skip(n)` | garde les n premiers / saute les n premiers |
| `peek(consumer)` | regarder au passage (debug) |

## Opérations terminales (donnent le résultat)

| Méthode | À quoi ça sert |
|---|---|
| `toList()` | stream -> List |
| `collect(Collectors...)` | stream -> List, Set, Map, groupement... |
| `forEach(consumer)` | action sur chaque élément |
| `count()` | nombre d'éléments (long) |
| `sum() / average() / max() / min()` | sur un stream de nombres (mapToDouble(...).sum()) |
| `max(comparator) / min(comparator)` | retourne un Optional |
| `reduce(identité, opération)` | combine tous les éléments en un seul |
| `findFirst() / findAny()` | retourne un Optional |
| `anyMatch / allMatch / noneMatch` | retourne un boolean |

## Collectors

| Méthode | À quoi ça sert |
|---|---|
| `toList() / toSet()` | collecter dans une List / un Set (sans doublon) |
| `toMap(clé, valeur)` | collecter dans une Map |
| `groupingBy(f)` | Map<clé, List<élément>> |
| `groupingBy(f, counting())` | Map<clé, Long> : nombre par groupe |
| `groupingBy(f, summingDouble(g))` | Map<clé, Double> : cumul par groupe |
| `averagingDouble(g)` | moyenne |
| `mapping(f, toList())` | transformer avant de collecter (en aval d'un groupingBy) |
| `partitioningBy(predicate)` | deux groupes : true / false |
| `joining(", ")` | assembler des chaînes |

## Optional

| Méthode | À quoi ça sert |
|---|---|
| `Optional.ofNullable(x)` | enveloppe une valeur qui peut être null |
| `map(f) / filter(p)` | transforme / filtre le contenu s'il existe |
| `orElse(v) / orElseGet(supplier)` | valeur par défaut |
| `orElseThrow(() -> exception)` | exception si vide |
| `isPresent() / ifPresent(consumer)` | tester / agir si présent |

## Requêtes Spring Data

| Méthode | À quoi ça sert |
|---|---|
| `Méthode dérivée` | findTop5ByOrderByTotalDesc  |  findByStatutAndDateCommandeBetween(...) |
| `@Query (JPQL)` | select c from Commande c where ...  (on écrit les noms des classes et des attributs, pas des tables) |
| `JPQL : agrégats` | count(c), sum(c.total), group by, join c.client cl |
| `JPQL : sous-requêtes` | exists / not exists (select 1 from ... ), count(distinct ...) |
| `JPQL : constructeur DTO` | select new com.youcode.lambdas.dto.XxxDto(a, b, c) from ... |
| `Paramètres nommés` | where p.stock >= :stock  +  @Param("stock") int stock |
| `Pagination et tri` | PageRequest.of(page, taille, Sort.by(Direction.DESC, "prix"))  |  Page.getContent(), getTotalElements(), map(...) |

## Modèle de réflexion (mise en situation)

1. **Comprendre** : que demande l'énoncé ? Quelle est l'entrée, quelle est la sortie ?
2. **Décomposer** : filtrer ? transformer ? trier ? regrouper ? limiter ?
3. **Coder** : une opération de stream par étape, dans cet ordre, puis un terminal (toList, collect, sum...).