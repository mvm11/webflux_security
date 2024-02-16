package com.example.security.app.config.usecase;

import com.example.security.domain.model.user.gateway.UserGateway;
import com.example.security.domain.usecase.LogInUseCase;
import com.example.security.domain.usecase.SignUpUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SignUpUseCase signUpUseCase(UserGateway userGateway) {
        return new SignUpUseCase(userGateway);
    }

    @Bean
    public LogInUseCase logInUseCase(UserGateway userGateway) {
        return new LogInUseCase(userGateway);
    }
}