package io.hexlet.service;

import io.hexlet.model.User;
import io.hexlet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public Mono<User> update(int userId, User user) {
        user.setId(userId);
        return userRepository.save(user);
    }

    public Mono<Void> delete(Integer id) {
        return userRepository.deleteById(id);
    }
}
