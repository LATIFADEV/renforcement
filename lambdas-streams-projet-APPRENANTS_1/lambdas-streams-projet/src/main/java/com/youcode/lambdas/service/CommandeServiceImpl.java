package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.CommandeDto;
import com.youcode.lambdas.dto.StatutStatDto;
import com.youcode.lambdas.entity.Commande;
import com.youcode.lambdas.entity.Paiement;
import com.youcode.lambdas.entity.StatutCommande;
import com.youcode.lambdas.entity.TypePaiement;
import com.youcode.lambdas.exception.RessourceIntrouvableException;
import com.youcode.lambdas.mapper.CommandeMapper;
import com.youcode.lambdas.repository.CommandeRepository;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository repository;
    private final CommandeMapper mapper;

    public CommandeServiceImpl(CommandeRepository repository, CommandeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CommandeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public CommandeDto findById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RessourceIntrouvableException("Commande", id));
    }

    @Override
    public List<CommandeDto> top5CommandesStream() {
        // TODO exercice 7 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 7");
    }

    @Override
    public List<CommandeDto> top5CommandesDerivee() {

        // TODO exercice 7 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 7");
    }

    @Override
    public Map<StatutCommande, Long> nombreParStatutStream() {
        // TODO exercice 8 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 8");
    }

    @Override
    public Map<StatutCommande, Double> cumulParStatutStream() {
        // TODO exercice 8 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 8");
    }

    @Override
    public List<StatutStatDto> statsParStatutJpql() {
        // TODO exercice 8 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 8");
    }

    @Override
    public Optional<ClientCommandeCountDto> clientPlusActif() {
        // TODO exercice 9 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 9");
    }

    @Override
    public List<CommandeDto> confirmeesEntre(LocalDate debut, LocalDate fin) {
        // TODO exercice 9 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 9");
    }

    @Override
    public List<String> top5ClientsParCommandeChere() {
        // TODO exercice 10 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 10");
    }

    @Override
    public List<CommandeDto> troisTypesPaiementStream() {
        // TODO exercice 11 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 11");
    }

    @Override
    public List<CommandeDto> troisTypesPaiementJpql() {
        // TODO exercice 11 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 11");
    }
}
