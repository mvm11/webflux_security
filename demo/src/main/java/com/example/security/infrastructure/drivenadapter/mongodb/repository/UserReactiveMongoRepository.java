package com.example.security.infrastructure.drivenadapter.mongodb.repository;

import com.example.security.infrastructure.drivenadapter.mongodb.document.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveMongoRepository extends ReactiveMongoRepository<UserDocument, String> {
    Mono<UserDocument> findByEmail(String email);

}