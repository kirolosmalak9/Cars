package com.iti.cars.utilities;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iti.cars.model.CarResponse;

import org.jetbrains.annotations.NotNull;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static JsonQ jsonQ;

    public static JsonQ getJsonQ() {
        if (jsonQ == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URI)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            jsonQ = retrofit.create(JsonQ.class);
        }
        return jsonQ;
    }

    public static void parsJson(Call<CarResponse> cars,
                                OnResponseRetrofit onResponseRetrofit) {

        cars.enqueue(new Callback<CarResponse>() {

            @Override
            public void onResponse(@NotNull Call<CarResponse> call,
                                   @NotNull Response<CarResponse> response) {

                CarResponse cars = response.body();
                if (cars != null) {
                    if (cars.getCars() != null) {
                        onResponseRetrofit.onResponse(cars.getCars());
                    }
                } else {
                    onResponseRetrofit.onFail(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CarResponse> call, @NotNull Throwable t) {
                t.printStackTrace();
                onResponseRetrofit.onFail("Fail");
            }
        });
    }

}
