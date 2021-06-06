package com.iti.cars.utilities;

import com.iti.cars.model.CarResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonQ {

    @GET("api/v1/cars")
    Call<CarResponse> getCars(@Query("page") int page);
}
