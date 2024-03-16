package com.jurisitsm.test.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    public RefreshToken(){}

    public RefreshToken(LocalDateTime expiryDate, AppUser user) {
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public AppUser getUser() {
        return user;
    }
}
