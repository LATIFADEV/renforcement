package com.youcode.formation.remise;

import org.springframework.stereotype.Component;

@Component
public class RemisePlatinum implements RemiseStrategy {
    @Override
    public String niveau() {
        return "PLATINUM";
    }

    @Override
    public double appliquer(double montant) {
        return montant * 0.85; // remise de 15 %
    }
}
