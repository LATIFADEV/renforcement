package com.youcode.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Package de base = com.youcode.formation : @ComponentScan par défaut scanne ce package et ses sous-packages. */
@SpringBootApplication
public class FormationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormationApplication.class, args);
    }
}
