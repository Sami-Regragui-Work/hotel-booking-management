package repository.impl;

import model.User;
import repository.UserRepository;

import java.util.*;
import java.util.stream.Stream;

public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> storage = new HashMap<>();
    private final Map<String, UUID> emailIndex = new HashMap<>();

    @Override
    public void save(User user) {
        UUID id = user.getId();
        this.storage.put(id, user);
        this.emailIndex.put(user.getEmail(), id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(this.storage.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(this.storage.get(this.emailIndex.get(email)));
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.storage.values().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public List<User> findAll() {
        return this.storage.values().stream().toList();
    }
}
