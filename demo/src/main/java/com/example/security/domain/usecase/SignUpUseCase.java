package com.example.security.domain.usecase;

import com.example.security.domain.model.dto.SignUpDTO;
import com.example.security.domain.model.user.User;
import com.example.security.domain.model.user.gateway.UserGateway;
import reactor.core.publisher.Mono;

public class SignUpUseCase {

    private final UserGateway userGateway;


    public SignUpUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Mono<User> signUp(SignUpDTO dto) {
        return userGateway.signUp(dto);
    }
}
