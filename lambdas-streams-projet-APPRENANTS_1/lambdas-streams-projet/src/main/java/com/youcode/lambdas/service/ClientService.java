package com.youcode.lambdas.service;

import com.youcode.lambdas.dto.ClientCommandeCountDto;
import com.youcode.lambdas.dto.ClientDto;
import java.util.List;

public interface ClientService {
    List<ClientDto> findAll();

    /** EXO 12 : noms des clients sans commande, en JPQL. */
    List<String> sansCommandeJpql();

    /** EXO 12 : noms des clients sans commande, avec un STREAM. */
    List<String> sansCommandeStream();

    /** EXO 12 : nombre de commandes par client, en JPQL. */
    List<ClientCommandeCountDto> nombreCommandesParClientJpql();
}
