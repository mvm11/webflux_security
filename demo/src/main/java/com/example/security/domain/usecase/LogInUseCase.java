package com.example.security.domain.usecase;

import com.example.security.domain.model.dto.LogInDTO;
import com.example.security.domain.model.dto.TokenDTO;
import com.example.security.domain.model.user.gateway.UserGateway;
import reactor.core.publisher.Mono;

public class LogInUseCase {


    private final UserGateway userGateway;

    public LogInUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Mono<TokenDTO> login(LogInDTO dto) {
        return userGateway.login(dto);
    }
}