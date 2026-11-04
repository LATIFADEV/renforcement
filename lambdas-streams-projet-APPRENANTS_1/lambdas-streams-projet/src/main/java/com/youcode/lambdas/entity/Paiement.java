package com.youcode.lambdas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    private double total;
    private double montantTva;
    private LocalDate datePaiement;

    @ManyToOne
    private Commande commande;

    protected Paiement() {
    }

    /** La TVA (20 %) est incluse dans le total : montantTva = total / 6. */
    public Paiement(TypePaiement typePaiement, StatutPaiement statut, double total, LocalDate datePaiement) {
        this.typePaiement = typePaiement;
        this.statut = statut;
        this.total = total;
        this.montantTva = total / 6.0;
        this.datePaiement = datePaiement;
    }

    void setCommande(Commande commande) { this.commande = commande; }

    public Long getId() { return id; }
    public TypePaiement getTypePaiement() { return typePaiement; }
    public StatutPaiement getStatut() { return statut; }
    public double getTotal() { return total; }
    public double getMontantTva() { return montantTva; }
    public LocalDate getDatePaiement() { return datePaiement; }
    public Commande getCommande() { return commande; }
}
