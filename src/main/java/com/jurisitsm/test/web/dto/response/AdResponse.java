package com.jurisitsm.test.web.dto.response;

public class AdResponse {

    private String id;
    private String brand;
    private String type;
    private String description;
    private long price;

    public AdResponse(){}

    public AdResponse(String id, String brand, String type, String description, long price) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
