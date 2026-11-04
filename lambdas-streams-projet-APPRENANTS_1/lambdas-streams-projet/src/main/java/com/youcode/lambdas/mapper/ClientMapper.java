package com.youcode.lambdas.mapper;

import com.youcode.lambdas.dto.ClientDto;
import com.youcode.lambdas.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDto toDto(Client c) {
        return new ClientDto(c.getId(), c.getNom());
    }
}
