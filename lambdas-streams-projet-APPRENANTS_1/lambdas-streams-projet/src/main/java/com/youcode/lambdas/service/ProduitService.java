package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.PageDto;
import com.youcode.lambdas.dto.ProduitDto;
import com.youcode.lambdas.dto.ProduitRequest;
import com.youcode.lambdas.entity.Produit;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.data.domain.Pageable;

public interface ProduitService {
    List<ProduitDto> findAll();

    ProduitDto findById(Long id);

    ProduitDto create(ProduitRequest request);

    /**
     * EXO 2 : produits dont le stock est > 50, avec le prix +10 % (arrondi a 2 decimales), tries par prix decroissant.
     * Ne pas modifier l'entite : construire des ProduitDto.
     */
    List<ProduitDto> stockEleveAvecMajoration();

    /** EXO 6 : retourne les produits qui verifient le critere recu en parametre (une lambda). */
    List<ProduitDto> search(Predicate<Produit> critere);

    /**
     * EXO 13 : recherche par nom (contient, sans tenir compte des majuscules), prix min/max et stock minimum.
     * Tous les criteres sont facultatifs (null = pas de filtre). Resultat pagine et trie (le Pageable porte page, taille, tri).
     */
    PageDto<ProduitDto> rechercher(String nom, Double prixMin, Double prixMax, Integer stockMin, Pageable pageable);
}
