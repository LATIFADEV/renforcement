package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.ClientDto;
import com.youcode.lambdas.entity.Client;
import com.youcode.lambdas.mapper.ClientMapper;
import com.youcode.lambdas.repository.ClientRepository;
import com.youcode.lambdas.repository.CommandeRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CommandeRepository commandeRepository;
    private final ClientMapper mapper;

    public ClientServiceImpl(ClientRepository clientRepository, CommandeRepository commandeRepository,
                             ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.commandeRepository = commandeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<String> sansCommandeJpql() {
        // TODO exercice 12 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 12");
    }

    @Override
    public List<String> sansCommandeStream() {
        // TODO exercice 12 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 12");
    }

    @Override
    public List<ClientCommandeCountDto> nombreCommandesParClientJpql() {
        // TODO exercice 12 : ecrire le code ici (voir EXERCICES.md et docs/FICHE-STREAM-API.md)
        throw new UnsupportedOperationException("exercice 12");
    }
}
