package com.example.security.domain.model.user.gateway;

import com.example.security.domain.model.dto.LogInDTO;
import com.example.security.domain.model.dto.SignUpDTO;
import com.example.security.domain.model.dto.TokenDTO;
import com.example.security.domain.model.user.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<User> signUp(SignUpDTO dto);
    Mono<TokenDTO> login(LogInDTO dto);

}