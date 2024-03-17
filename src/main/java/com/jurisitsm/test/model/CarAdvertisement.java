package com.jurisitsm.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_advertisement")
public class CarAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String brand;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private long price;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AppUser author;

    public CarAdvertisement() {
    }

    public CarAdvertisement(String brand, String type, String description, long price, AppUser author) {
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public AppUser getAuthor() {
        return author;
    }
}
