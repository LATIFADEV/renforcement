package com.youcode.lambdas.util;

public final class Arrondi {
    private Arrondi() {
    }

    /** Arrondit a 2 decimales : Arrondi.deuxDecimales(12.3456) donne 12.35 */
    public static double deuxDecimales(double valeur) {
        return Math.round(valeur * 100.0) / 100.0;
    }
}
