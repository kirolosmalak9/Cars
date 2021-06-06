package com.iti.cars.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iti.cars.R;
import com.iti.cars.model.Car;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder>{


    private final Context context;
    private List<Car> cars;

    public CarsAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.carBrand.setText(cars.get(position).getBrand());

        if(cars.get(position).getUsed()) {
            holder.carIsUsed.setText(R.string.yes);
        } else {
            holder.carIsUsed.setText(R.string.no);
        }

        if(cars.get(position).getImageUrl() == null) {
            holder.carImage.setImageResource(R.drawable.ic_launcher_background);
        } else {
            Glide.with(context).load(cars.get(position).getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.carImage);
        }

        if(cars.get(position).getConstractionYear() == null) {
            holder.carConstructionYearLinearLayout.setVisibility(View.GONE);
        } else {
            holder.carConstructionYear.setText(cars.get(position).getConstractionYear());
        }

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView carImage;
        public TextView carBrand;
        public TextView carIsUsed;
        public TextView carConstructionYear;
        public LinearLayout carConstructionYearLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            carImage = itemView.findViewById(R.id.car_image);
            carBrand = itemView.findViewById(R.id.car_brand);
            carIsUsed = itemView.findViewById(R.id.car_isUsed);
            carConstructionYear = itemView.findViewById(R.id.car_constructionYear);
            carConstructionYearLinearLayout = itemView.findViewById(R.id.car_linear_layout);
        }
    }
}
