package com.jurisitsm.test.web.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class AdRequest {
    @Size(max = 20)
    private String brand;
    @Size(max = 20)
    private String type;
    @Size(max = 200)
    private String description;
    @Max(value = 9999999999L)
    private long price;

    public AdRequest(){}

    public AdRequest(String brand, String type, String description, long price) {
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
