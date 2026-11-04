package com.youcode.lambdas.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;

    @ManyToOne
    private Client client;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;
    private double total;
    private LocalDate dateCommande;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Paiement> paiements = new ArrayList<>();

    protected Commande() {
    }

    public Commande(String reference, Client client, StatutCommande statut, double total, LocalDate dateCommande) {
        this.reference = reference;
        this.client = client;
        this.statut = statut;
        this.total = total;
        this.dateCommande = dateCommande;
    }

    public void ajouterPaiement(Paiement p) {
        paiements.add(p);
        p.setCommande(this);
    }

    public Long getId() { return id; }
    public String getReference() { return reference; }
    public Client getClient() { return client; }
    public StatutCommande getStatut() { return statut; }
    public double getTotal() { return total; }
    public LocalDate getDateCommande() { return dateCommande; }
    public List<Paiement> getPaiements() { return paiements; }
}
