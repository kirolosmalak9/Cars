package com.iti.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.iti.cars.adapter.CarsAdapter;
import com.iti.cars.model.Car;
import com.iti.cars.utilities.Network;
import com.iti.cars.utilities.OnResponseRetrofit;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements OnResponseRetrofit {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<List<Car>> cars = Network.getJsonQ().getCars(1);

        Network.parsJson(cars, this);

        recyclerView = findViewById(R.id.cars_recyclerview);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResponse(List<Car> cars) {
        CarsAdapter carsAdapter = new CarsAdapter(this, cars);
        recyclerView.setAdapter(carsAdapter);
    }

    @Override
    public void onFail() {
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
    }
}