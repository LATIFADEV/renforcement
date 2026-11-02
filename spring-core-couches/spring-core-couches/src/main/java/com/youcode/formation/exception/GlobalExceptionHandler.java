package com.youcode.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Traduit les exceptions métier en codes HTTP : 404, 409, 400. */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommandeIntrouvableException.class)
    public ResponseEntity<ErreurApi> introuvable(CommandeIntrouvableException e) {
        return reponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ReferenceDejaUtiliseeException.class)
    public ResponseEntity<ErreurApi> conflit(ReferenceDejaUtiliseeException e) {
        return reponse(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErreurApi> validation(MethodArgumentNotValidException e) {
        String detail = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " : " + f.getDefaultMessage())
                .reduce((a, b) -> a + " ; " + b).orElse("requête invalide");
        return reponse(HttpStatus.BAD_REQUEST, detail);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErreurApi> requeteInvalide(IllegalArgumentException e) {
        return reponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<ErreurApi> reponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErreurApi(status.value(), message));
    }
}
