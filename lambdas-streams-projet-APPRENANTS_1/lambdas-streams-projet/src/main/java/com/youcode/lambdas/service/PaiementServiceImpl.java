package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.PaiementDto;
import com.youcode.lambdas.entity.Paiement;
import com.youcode.lambdas.entity.StatutPaiement;
import com.youcode.lambdas.entity.TypePaiement;
import com.youcode.lambdas.mapper.PaiementMapper;
import com.youcode.lambdas.repository.PaiementRepository;
import com.youcode.lambdas.util.Arrondi;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository repository;
    private final PaiementMapper mapper;

    public PaiementServiceImpl(PaiementRepository repository, PaiementMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PaiementDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<PaiementDto> cashEncaisses() {
        // Methodes utiles : filter (deux fois ou avec &&), sorted, Comparator.comparing(...).reversed(), map(mapper::toDto), toList
        // TODO exercice 3 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 3");
    }

    @Override
    public List<PaiementDto> enAttenteAvecRemise() {
        // TODO exercice 4 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 4");
    }

    @Override
    public double totalTvaCollectee() {
        // TODO exercice 5 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 5");
    }
}
