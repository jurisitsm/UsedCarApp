package com.jurisitsm.test.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name="app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column
    private LocalDateTime lastLogoutTime;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<CarAdvertisement> advertisements;

    public AppUser(){}

    public AppUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.advertisements = Collections.emptySet();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setLastLogoutTime(LocalDateTime lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }
}
