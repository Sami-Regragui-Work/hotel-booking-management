package service;

import model.User;
import repository.UserRepository;
import util.ValidationUtils;

import java.util.Optional;

public class AuthService {
    private final UserRepository userRepository;
    private User currentUser;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String fullName, String email, String phone, String password) throws IllegalArgumentException {
        if (!ValidationUtils.isValidFullName(fullName)) throw new IllegalArgumentException("Invalid name");
        if (!ValidationUtils.isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        if (!ValidationUtils.isValidPhone(phone)) throw new IllegalArgumentException("Invalid phone");
        if (!ValidationUtils.isValidPassword(password)) throw new IllegalArgumentException("Invalid password");

        if (this.userRepository.existsByEmail(email)) throw new IllegalArgumentException("Email already exists");

        User newUser = new User(fullName, email, phone, password);

        this.userRepository.save(newUser);
    }

    public void login(String email, String password) throws IllegalArgumentException {
        Optional<User> user = this.userRepository.findByEmail(email);

        if (user.isEmpty() || !user.get().getPassword().equals(password)) throw new IllegalArgumentException("Invalid credentials");

        this.currentUser = user.get();
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return  currentUser;
    }

    public boolean isLoggedIn() {
        return  currentUser != null;
    }
}
