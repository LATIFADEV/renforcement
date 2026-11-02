package com.youcode.formation.service;

import com.youcode.formation.exception.CommandeIntrouvableException;
import com.youcode.formation.exception.ReferenceDejaUtiliseeException;
import com.youcode.formation.model.Commande;
import com.youcode.formation.model.CommandeRequest;
import com.youcode.formation.remise.RemiseService;
import com.youcode.formation.repository.CommandeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommandeCrudServiceImpl implements CommandeCrudService {
    private final CommandeRepository repository;
    private final RemiseService remiseService;

    public CommandeCrudServiceImpl(CommandeRepository repository, RemiseService remiseService) {
        this.repository = repository;
        this.remiseService = remiseService;
    }

    @Override
    public List<Commande> lister() {
        return repository.findAll();
    }

    @Override
    public Commande trouver(Long id) {
        return repository.findById(id).orElseThrow(() -> new CommandeIntrouvableException(id));
    }

    @Override
    public Commande creer(CommandeRequest r) {
        if (repository.existsByReference(r.reference())) {
            throw new ReferenceDejaUtiliseeException(r.reference());
        }
        double montantFinal = remiseService.appliquer(r.niveau(), r.montant());
        return repository.save(new Commande(null, r.reference(), r.client(), r.montant(),
                r.niveau().toUpperCase(), montantFinal));
    }

    @Override
    public void supprimer(Long id) {
        if (!repository.deleteById(id)) {
            throw new CommandeIntrouvableException(id);
        }
    }
}
