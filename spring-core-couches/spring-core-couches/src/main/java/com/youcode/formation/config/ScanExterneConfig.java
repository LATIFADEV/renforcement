package com.youcode.formation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * EXO 3 : on ajoute un package à scanner.
 * Alternative : @Import(AuditExterne.class) pour importer une classe précise.
 * Pour prouver le problème : commenter @ComponentScan -> NoSuchBeanDefinitionException au démarrage.
 */
@Configuration
@ComponentScan(basePackages = "com.youcode.externe")
public class ScanExterneConfig {
}
