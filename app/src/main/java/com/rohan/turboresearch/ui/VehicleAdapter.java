package com.rohan.turboresearch.ui;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rohan.turboresearch.R;
import com.rohan.turboresearch.room.entity.Cars;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    List<Cars> cars;
    Context context;

    VehicleAdapter(Context context) {
        this.context = context;
        this.cars = new ArrayList<>();
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VehicleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Cars car = cars.get(position);
        File f = new File(car.getPath());
        holder.image.setImageURI(Uri.fromFile(f));
        holder.number.setText(car.getNumber());
        holder.time.setText(car.getTime());
        holder.date.setText(car.getDate());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView number, time, date;
        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            number = itemView.findViewById(R.id.number);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
        }
    }

    public void setData(List<Cars> cars) {
        this.cars = cars;
    }
}
