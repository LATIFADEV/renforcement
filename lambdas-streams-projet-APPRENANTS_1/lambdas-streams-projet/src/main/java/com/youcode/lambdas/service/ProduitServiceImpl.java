package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.PageDto;
import com.youcode.lambdas.dto.ProduitDto;
import com.youcode.lambdas.dto.ProduitRequest;
import com.youcode.lambdas.entity.Produit;
import com.youcode.lambdas.exception.RessourceIntrouvableException;
import com.youcode.lambdas.mapper.ProduitMapper;
import com.youcode.lambdas.repository.ProduitRepository;
import com.youcode.lambdas.util.Arrondi;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository repository;
    private final ProduitMapper mapper;

    public ProduitServiceImpl(ProduitRepository repository, ProduitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProduitDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public ProduitDto findById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RessourceIntrouvableException("Produit", id));
    }

    @Override
    @Transactional
    public ProduitDto create(ProduitRequest request) {
        return mapper.toDto(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<ProduitDto> stockEleveAvecMajoration() {
        // Methodes utiles : filter, map, sorted, Comparator.comparingDouble(...).reversed(), toList
        // TODO exercice 2 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 2");
    }

    @Override
    public List<ProduitDto> search(Predicate<Produit> critere) {
        // TODO exercice 6 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 6");
    }

    @Override
    public PageDto<ProduitDto> rechercher(String nom, Double prixMin, Double prixMax, Integer stockMin, Pageable pageable) {
        // TODO exercice 13 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 13");
    }
}
