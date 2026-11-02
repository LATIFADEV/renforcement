package com.youcode.formation.remise;

import org.springframework.stereotype.Component;

@Component
public class RemiseSilver implements RemiseStrategy {
    @Override
    public String niveau() {
        return "SILVER";
    }

    @Override
    public double appliquer(double montant) {
        return montant * 0.95; // remise de 5 %
    }
}
