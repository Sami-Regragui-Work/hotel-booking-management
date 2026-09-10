package model;

import java.util.Locale;
import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private String password;

    public User(String fullName, String email, String phone, String password) throws IllegalArgumentException {
        this.generateId();
        this.setFullName(fullName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setPassword(password);
    }

    public UUID getId() {
        return id;
    }

    public void generateId() {
        this.id = UUID.randomUUID();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) throws IllegalArgumentException {
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Empty fullname");
        this.fullName = fullName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Empty email");
        if (!email.contains("@") || !email.contains("."))
            throw new IllegalArgumentException("Invalid email format");

        this.email = email.trim().toLowerCase();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws IllegalArgumentException {
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Empty phone number");
        this.phone = phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }
        this.password = password;
    }
}
