package com.backend.basespring.repository;

import com.backend.basespring.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}
