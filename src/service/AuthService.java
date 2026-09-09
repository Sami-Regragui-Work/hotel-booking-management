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

    public void updateProfile(String fullName, String email, String phone) throws IllegalArgumentException {
        if (fullName != null) {
            if (!ValidationUtils.isValidFullName(fullName)) throw new IllegalArgumentException("Invalid name");

            this.currentUser.setFullName(fullName);
        }

        if (email != null) {
            if (!ValidationUtils.isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
            if (this.userRepository.existsByEmail(email)) throw new IllegalArgumentException("Email already exists");

            this.currentUser.setEmail(email);
        }

        if (phone != null) {
            if (!ValidationUtils.isValidPhone(phone)) throw new IllegalArgumentException("Invalid phone");

            this.currentUser.setPhone(phone);
        }

        this.userRepository.save(this.currentUser);
    }

    public void changePassword(String oldPassword, String newPassword) throws IllegalArgumentException {
        if (!oldPassword.equals(this.currentUser.getPassword())) throw new IllegalArgumentException("Old password is incorrect");
        if (!ValidationUtils.isValidPassword(newPassword)) throw new IllegalArgumentException("Invalid new password");

        this.currentUser.setPassword(newPassword);

        this.userRepository.save(this.currentUser);
    }


}
