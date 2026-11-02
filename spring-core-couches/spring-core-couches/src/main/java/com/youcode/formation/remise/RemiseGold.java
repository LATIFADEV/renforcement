package com.youcode.formation.remise;

import org.springframework.stereotype.Component;

@Component
public class RemiseGold implements RemiseStrategy {
    @Override
    public String niveau() {
        return "GOLD";
    }

    @Override
    public double appliquer(double montant) {
        return montant * 0.90; // remise de 10 %
    }
}
