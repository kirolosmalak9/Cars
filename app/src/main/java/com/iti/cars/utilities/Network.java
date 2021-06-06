package com.iti.cars.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iti.cars.model.Car;

import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    public static void parsJson(Call<List<Car>> cars,
                                    OnResponseRetrofit onResponseRetrofit) {

        cars.enqueue(new Callback<List<Car>>() {

            @Override
            public void onResponse(@NotNull Call<List<Car>> call,
                                   @NotNull Response<List<Car>> response) {

                List<Car> cars  = response.body();
                onResponseRetrofit.onResponse(cars);
            }
            @Override
            public void onFailure(@NotNull Call<List<Car>> call, @NotNull Throwable t) {
                t.printStackTrace();
                onResponseRetrofit.onFail();
            }
        });
    }

}
