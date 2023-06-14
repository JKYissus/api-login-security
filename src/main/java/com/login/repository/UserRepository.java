package com.login.repository;

import com.login.entitie.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserSession, String> {
    UserSession findByUsername(String username);
}
