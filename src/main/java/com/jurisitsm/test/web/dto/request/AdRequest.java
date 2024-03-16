package com.jurisitsm.test.web.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class AdRequest {
    @Size(max = 20, message = "Brand name should not be longer than 20 characters.")
    private String brand;
    @Size(max = 20, message = "Type should not be longer than 20 characters.")
    private String type;
    @Size(max = 200, message = "The description of the vehicle for sale should not be longer than 200 characters.")
    private String description;
    @Max(value = 9999999999L, message = "The price of the vehicle for sale can not be more than ten digits.")
    private long price;

    public AdRequest(){}

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
