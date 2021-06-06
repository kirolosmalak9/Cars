package com.iti.cars;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.iti.cars.adapter.CarsAdapter;
import com.iti.cars.model.Car;
import com.iti.cars.model.CarResponse;
import com.iti.cars.utilities.AddNewData;
import com.iti.cars.utilities.Network;
import com.iti.cars.utilities.OnResponseRetrofit;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements OnResponseRetrofit, AddNewData {

    private CarsAdapter carsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private int page = 1;
    private int pageLimit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_circular);


        RecyclerView recyclerView = findViewById(R.id.cars_recyclerview);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        carsAdapter = new CarsAdapter(this, this);
        recyclerView.setAdapter(carsAdapter);


        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            pageLimit = 0;
            page = 1;
            carsAdapter.removeAllCars();
            getData(page);
        });

        getData(page);


    }

    @Override
    public void onResponse(List<Car> cars) {
        carsAdapter.addCars(cars);
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFail(String text) {
        if (text != null)
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void getData(int page) {
        Call<CarResponse> call = Network.getJsonQ().getCars(page);
        Network.parsJson(call, this);
    }

    @Override
    public void getCars() {
        if (pageLimit < 3) {
            progressBar.setVisibility(View.VISIBLE);
            //page++;
            pageLimit++;
            getData(page);
        }
    }
}