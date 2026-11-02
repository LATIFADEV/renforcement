# Exo 11 : schéma des couches (présentation en 2 minutes)

```
        Client (Swagger UI / Postman)
                    |  HTTP + JSON
                    v
        +-------------------------+
        |  DispatcherServlet      |   route la requête
        +-------------------------+
                    v
        +-------------------------+
        |  CONTROLLER             |   CommandeController, RemiseController
        |  HTTP uniquement        |   codes 200 / 201 / 204 / 400 / 404 / 409
        +-------------------------+
                    v   (interface)
        +-------------------------+
        |  SERVICE                |   CommandeCrudService -> RemiseService
        |  logique métier         |   RemiseService -> RemiseStrategy (Silver, Gold, Platinum)
        +-------------------------+
                    v   (interface)
        +-------------------------+
        |  REPOSITORY             |   CommandeRepository -> InMemoryCommandeRepository
        |  accès aux données      |
        +-------------------------+
```

Chaque flèche passe par une **interface** : une couche dépend d'une abstraction (DIP), jamais d'une classe concrète.
`GlobalExceptionHandler` (@RestControllerAdvice) transforme les exceptions métier en codes HTTP.

## Script oral (≈ 2 minutes)

1. **(20 s)** Une requête arrive au DispatcherServlet, qui la confie au controller.
2. **(30 s)** Le controller ne fait que du HTTP : il valide (@Valid), appelle le service, renvoie le bon code (201, 404...).
3. **(40 s)** Le service porte la règle métier. La remise de fidélité utilise le pattern Strategy : pour ajouter
   un niveau (ex. BRONZE), on crée une classe, sans toucher au service (Open/Closed).
4. **(20 s)** Le repository isole le stockage. Il est en mémoire ici ; passer à JPA/PostgreSQL ne change ni service ni controller.
5. **(10 s)** Conclusion : une couche = une responsabilité (SRP), des interfaces partout (DIP), du code facile à tester.
