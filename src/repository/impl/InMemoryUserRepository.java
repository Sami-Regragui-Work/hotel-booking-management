package repository.impl;

import model.User;
import repository.UserRepository;

import java.util.*;
import java.util.stream.Stream;

public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> storage = new HashMap<>();

    @Override
    public void save(User user) {
        this.storage.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(this.storage.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.storage.values().stream().filter(user -> user.getEmail().equals(email)).findFirst();
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
