package ru.romazanov.screens.location;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.romazanov.data.model.location.Location;
import ru.romazanov.therickandmortyjava.databinding.LocationItemBinding;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private ArrayList<Location> dataList = new ArrayList<>();

    public void setDataList(ArrayList<Location> list) {
        dataList.addAll(list);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       LocationItemBinding binding = LocationItemBinding.inflate(layoutInflater, parent, false);
       return new LocationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.binding.idView.setText(String.valueOf(dataList.get(position).id));
        holder.binding.nameView.setText(dataList.get(position).name);
        holder.binding.dimensionView.setText(dataList.get(position).dimension);

    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        LocationItemBinding binding;
        public LocationViewHolder(@NonNull  LocationItemBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
