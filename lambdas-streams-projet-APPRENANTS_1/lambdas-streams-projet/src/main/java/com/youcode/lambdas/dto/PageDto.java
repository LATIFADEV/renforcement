package com.youcode.lambdas.dto;

import java.util.List;
import org.springframework.data.domain.Page;

/** Reponse paginee de l'API (on n'expose pas directement l'objet Page de Spring Data). */
public record PageDto<T>(List<T> content, int page, int size, long totalElements, int totalPages) {
    public static <T> PageDto<T> of(Page<T> p) {
        return new PageDto<>(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements(), p.getTotalPages());
    }
}
