package com.iti.cars.utilities;

import com.iti.cars.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonQ {

    @GET("api/v1/cars?page={page}")
    Call<List<Car>> getCars(@Path("page") int page);
}
