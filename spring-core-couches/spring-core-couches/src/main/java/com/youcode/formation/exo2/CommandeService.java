package com.youcode.formation.exo2;

/** EXO 2 : une interface, deux implémentations -> ambiguïté pour Spring. */
public interface CommandeService {
    String traiter(String reference);
}
