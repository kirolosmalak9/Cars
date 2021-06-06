package com.iti.cars.model;

public class Car {

    private int id;
    private String brand;
    private String ConstractionYear;
    private String imageUrl;
    private Boolean IsUsed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstractionYear() {
        return ConstractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        ConstractionYear = constractionYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getUsed() {
        return IsUsed;
    }

    public void setUsed(Boolean used) {
        IsUsed = used;
    }
}
