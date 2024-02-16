package com.example.security.infrastructure.entrypoint.reactiveweb.auth;

import com.example.security.domain.model.dto.LogInDTO;
import com.example.security.domain.model.dto.SignUpDTO;
import com.example.security.domain.model.dto.TokenDTO;
import com.example.security.domain.model.user.User;
import com.example.security.domain.usecase.LogInUseCase;
import com.example.security.domain.usecase.SignUpUseCase;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AuthHandler {

    private final SignUpUseCase signUpUseCase;
    private final LogInUseCase logInUseCase;

    public AuthHandler(SignUpUseCase signUpUseCase, LogInUseCase logInUseCase) {
        this.signUpUseCase = signUpUseCase;
        this.logInUseCase = logInUseCase;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> hello(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Hello"), String.class);
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {

        return request.bodyToMono(SignUpDTO.class)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(signUpUseCase.signUp(dto), User.class));
    }

    public Mono<ServerResponse> logIn(ServerRequest request) {

        return request.bodyToMono(LogInDTO.class)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(logInUseCase.login(dto), TokenDTO.class));
    }

}