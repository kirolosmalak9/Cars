package com.iti.cars.model;

import java.util.List;

public class CarResponse {

    private int status;
    private List<Car> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Car> getCars() {
        return data;
    }

    public void setCars(List<Car> cars) {
        this.data = cars;
    }
}
