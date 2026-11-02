package com.youcode.formation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CommandeApiTest {
    @Autowired
    private MockMvc mvc;

    private static String json(String ref, double montant, String niveau) {
        return "{\"reference\":\"" + ref + "\",\"client\":\"Aya\",\"montant\":" + montant
                + ",\"niveau\":\"" + niveau + "\"}";
    }

    @Test
    void creation_201_puis_doublon_409() throws Exception {
        mvc.perform(post("/api/commandes").contentType(MediaType.APPLICATION_JSON).content(json("T-1", 100, "GOLD")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.montantFinal").value(90.0));
        mvc.perform(post("/api/commandes").contentType(MediaType.APPLICATION_JSON).content(json("T-1", 100, "GOLD")))
                .andExpect(status().isConflict());
    }

    @Test
    void donnees_invalides_400() throws Exception {
        mvc.perform(post("/api/commandes").contentType(MediaType.APPLICATION_JSON).content(json("T-2", -5, "GOLD")))
                .andExpect(status().isBadRequest());
        mvc.perform(post("/api/commandes").contentType(MediaType.APPLICATION_JSON).content(json("T-3", 50, "DIAMANT")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void commande_inexistante_404() throws Exception {
        mvc.perform(get("/api/commandes/999")).andExpect(status().isNotFound());
    }

    @Test
    void remise_200() throws Exception {
        mvc.perform(get("/api/remises").param("niveau", "platinum").param("montant", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montantFinal").value(170.0));
    }
}
