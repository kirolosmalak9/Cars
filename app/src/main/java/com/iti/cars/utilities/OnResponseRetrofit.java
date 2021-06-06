package com.iti.cars.utilities;


import com.iti.cars.model.Car;

import java.util.List;

public interface OnResponseRetrofit {
    void onResponse(List<Car> cars);

    void onFail(String text);
}
