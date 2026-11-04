package com.youcode.lambdas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.CommandeDto;
import com.youcode.lambdas.dto.PageDto;
import com.youcode.lambdas.dto.PaiementDto;
import com.youcode.lambdas.dto.ProduitDto;
import com.youcode.lambdas.dto.StatutStatDto;
import com.youcode.lambdas.entity.StatutCommande;
import com.youcode.lambdas.service.ClientService;
import com.youcode.lambdas.service.CommandeService;
import com.youcode.lambdas.service.PaiementService;
import com.youcode.lambdas.service.ProduitService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/** Un test vert = l'exercice est reussi. (Les exercices 1 se verifient dans la console.) */
@SpringBootTest
class ExercicesTest {
    @Autowired ProduitService produits;
    @Autowired PaiementService paiements;
    @Autowired CommandeService commandes;
    @Autowired ClientService clients;

    @Test
    void exo2_stock_eleve_prix_majore() {
        List<ProduitDto> r = produits.stockEleveAvecMajoration();
        assertThat(r).hasSize(7);
        assertThat(r.get(0).nom()).isEqualTo("Casque");
        assertThat(r.get(0).prix()).isEqualTo(330.0);
        assertThat(r).isSortedAccordingTo((a, b) -> Double.compare(b.prix(), a.prix()));
    }

    @Test
    void exo3_cash_encaisses() {
        List<PaiementDto> r = paiements.cashEncaisses();
        assertThat(r).hasSize(5);
        assertThat(r.get(0).total()).isEqualTo(950.0);
        assertThat(r.get(0).date()).isEqualTo(LocalDate.parse("2026-10-22"));
    }

    @Test
    void exo4_en_attente_avec_remise() {
        List<PaiementDto> r = paiements.enAttenteAvecRemise();
        assertThat(r).hasSize(7);
        assertThat(r.get(0).date()).isEqualTo(LocalDate.parse("2026-10-27"));
        assertThat(r.get(0).total()).isEqualTo(31.5);
    }

    @Test
    void exo5_tva_collectee() {
        assertThat(paiements.totalTvaCollectee()).isCloseTo(1711.67, within(0.01));
    }

    @Test
    void exo6_search_avec_une_lambda() {
        assertThat(produits.search(p -> p.getStock() == 0)).extracting(ProduitDto::nom).containsExactly("Stabilo");
        assertThat(produits.search(p -> p.getNom().startsWith("S"))).hasSize(5);
    }

    @Test
    void exo7_top5_stream_et_derivee() {
        List<CommandeDto> r = commandes.top5CommandesStream();
        assertThat(r).extracting(CommandeDto::total).containsExactly(3100.0, 2500.0, 1800.0, 1200.0, 950.0);
        assertThat(commandes.top5CommandesDerivee()).isEqualTo(r);
    }

    @Test
    void exo8_par_statut() {
        assertThat(commandes.nombreParStatutStream()).containsEntry(StatutCommande.CONFIRMED, 8L)
                .containsEntry(StatutCommande.PENDING, 4L).containsEntry(StatutCommande.CANCELLED, 2L);
        assertThat(commandes.cumulParStatutStream().get(StatutCommande.CONFIRMED)).isEqualTo(10690.0);
        List<StatutStatDto> jpql = commandes.statsParStatutJpql();
        assertThat(jpql).hasSize(3);
        assertThat(jpql).filteredOn(s -> s.statut() == StatutCommande.PENDING)
                .extracting(StatutStatDto::nombre, StatutStatDto::total).containsExactly(org.assertj.core.groups.Tuple.tuple(4L, 725.0));
    }

    @Test
    void exo9_client_le_plus_actif_et_variante() {
        assertThat(commandes.clientPlusActif()).contains(new ClientCommandeCountDto("Aya", 4L));
        assertThat(commandes.confirmeesEntre(LocalDate.parse("2026-09-01"), LocalDate.parse("2026-09-30")))
                .extracting(CommandeDto::reference).containsExactlyInAnyOrder("CMD-01", "CMD-04", "CMD-06");
    }

    @Test
    void exo10_top5_clients() {
        assertThat(commandes.top5ClientsParCommandeChere())
                .containsExactly("Hajar", "Omar", "Youssef", "Aya", "Salma");
    }

    @Test
    void exo11_trois_types_de_paiement() {
        assertThat(commandes.troisTypesPaiementStream()).extracting(CommandeDto::reference)
                .containsExactlyInAnyOrder("CMD-04", "CMD-10");
        assertThat(commandes.troisTypesPaiementJpql()).extracting(CommandeDto::reference)
                .containsExactlyInAnyOrder("CMD-04", "CMD-10");
    }

    @Test
    void exo12_clients_sans_commande_et_comptage() {
        assertThat(clients.sansCommandeJpql()).containsExactlyInAnyOrder("Karim", "Nadia");
        assertThat(clients.sansCommandeStream()).containsExactlyInAnyOrder("Karim", "Nadia");
        assertThat(clients.nombreCommandesParClientJpql()).hasSize(5)
                .contains(new ClientCommandeCountDto("Aya", 4L), new ClientCommandeCountDto("Omar", 3L));
    }

    @Test
    void exo13_recherche_paginee_et_triee() {
        PageDto<ProduitDto> r = produits.rechercher("sou", null, null, null,
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "prix")));
        assertThat(r.content()).extracting(ProduitDto::nom).containsExactly("Souris Gamer", "Souris");

        PageDto<ProduitDto> p2 = produits.rechercher(null, null, null, 51,
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "prix")));
        assertThat(p2.totalElements()).isEqualTo(7);
        assertThat(p2.content()).hasSize(5);
        assertThat(p2.content().get(0).nom()).isEqualTo("Casque");

        PageDto<ProduitDto> p3 = produits.rechercher(null, 100.0, 500.0, null, PageRequest.of(0, 20));
        assertThat(p3.content()).extracting(ProduitDto::nom)
                .containsExactlyInAnyOrder("Souris", "Clavier", "Casque", "Souris Gamer");
    }
}
