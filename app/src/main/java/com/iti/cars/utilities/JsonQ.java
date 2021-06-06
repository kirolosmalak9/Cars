package com.iti.cars.utilities;

import com.iti.cars.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonQ {

    @GET("api/v1/cars/")
    Call<List<Car>> getCars(@Query("page") String page);
}
