package com.youcode.formation.exo6;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public String message(String nom) {
        return "Bonjour " + nom;
    }
}
