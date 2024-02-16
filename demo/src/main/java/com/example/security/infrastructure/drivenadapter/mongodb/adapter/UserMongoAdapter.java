package com.example.security.infrastructure.drivenadapter.mongodb.adapter;


import com.example.security.domain.model.dto.LogInDTO;
import com.example.security.domain.model.dto.SignUpDTO;
import com.example.security.domain.model.dto.TokenDTO;
import com.example.security.domain.model.user.User;
import com.example.security.domain.model.user.gateway.UserGateway;
import com.example.security.infrastructure.drivenadapter.mongodb.document.UserDocument;
import com.example.security.infrastructure.drivenadapter.mongodb.mapper.UserMapper;
import com.example.security.infrastructure.drivenadapter.mongodb.repository.UserReactiveMongoRepository;
import com.example.security.infrastructure.drivenadapter.security.jwt.provider.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserMongoAdapter implements UserGateway {

    private final UserReactiveMongoRepository userReactiveMongoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserMongoAdapter(UserReactiveMongoRepository userReactiveMongoRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userReactiveMongoRepository = userReactiveMongoRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }


    @Override
    public Mono<User> signUp(SignUpDTO dto) {
        var userDocument = getUserDocument(dto);

        Mono<Boolean> userExists = userReactiveMongoRepository.findByEmail(userDocument.getEmail()).hasElement();
        return userExists
                .flatMap(exists -> exists ?
                        Mono.error(new Throwable("email already in use"))
                        : userReactiveMongoRepository.save(userDocument))
                .map(UserMapper::mapToModel);
    }

    private UserDocument getUserDocument(SignUpDTO dto) {
        return new UserDocument(
                null,
                dto.name(),
                dto.lastName(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                true,
                "ADMIN");
    }

    @Override
    public Mono<TokenDTO> login(LogInDTO dto) {
        return userReactiveMongoRepository.findByEmail(dto.email())
                .filter(userDocument -> passwordEncoder.matches(dto.password(), userDocument.getPassword()))
                .map(userDocument -> new TokenDTO(jwtProvider.generateToken(userDocument)))
                .switchIfEmpty(Mono.error(new Throwable("bad credentials")));
    }
}