package com.youcode.lambdas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RessourceIntrouvableException.class)
    public ResponseEntity<ErreurApi> introuvable(RessourceIntrouvableException e) {
        return reponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /** Un exercice non encore code : le projet demarre, l'endpoint repond 501 avec le numero de l'exercice. */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ErreurApi> aFaire(UnsupportedOperationException e) {
        return reponse(HttpStatus.NOT_IMPLEMENTED, "A FAIRE : " + e.getMessage());
    }

    /** Requete invalide (par exemple champ de tri inconnu). */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErreurApi> requeteInvalide(IllegalArgumentException e) {
        return reponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<ErreurApi> reponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErreurApi(status.value(), message));
    }
}
