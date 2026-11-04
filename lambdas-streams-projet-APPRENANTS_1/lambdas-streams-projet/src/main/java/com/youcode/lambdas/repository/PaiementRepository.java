package com.youcode.lambdas.repository;

import com.youcode.lambdas.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
