package com.example.security.infrastructure.drivenadapter.mongodb.mapper;

import com.example.security.domain.model.user.User;
import com.example.security.infrastructure.drivenadapter.mongodb.document.UserDocument;

public class UserMapper {

    private UserMapper() {}

    public static User mapToModel(UserDocument userDocument) {
        return new User(userDocument.getId(),
                userDocument.getName(),
                userDocument.getLastName(),
                userDocument.getEmail(),
                userDocument.getPassword(),
                userDocument.getStatus(),
                userDocument.getRoles());
    }

    public static UserDocument mapToModel(User user) {
        return new UserDocument(user.id(),
                user.name(),
                user.lastName(),
                user.email(),
                user.password(),
                user.status(),
                user.roles());
    }
}