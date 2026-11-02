package com.youcode.externe;

import org.springframework.stereotype.Component;

/**
 * EXO 3 : ce bean est dans com.youcode.externe, EN DEHORS du package de base com.youcode.formation.
 * Sans configuration il n'est PAS détecté. Il l'est grâce à config/ScanExterneConfig.
 */
@Component
public class AuditExterne {
    public String info() {
        return "AuditExterne détecté (package hors du package de base)";
    }
}
