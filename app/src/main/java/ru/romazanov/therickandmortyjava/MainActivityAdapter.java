package ru.romazanov.therickandmortyjava;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.romazanov.data.room.entities.MyInterceptorEntity;
import ru.romazanov.therickandmortyjava.databinding.LogItemBinding;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {


    private List<MyInterceptorEntity> list = new ArrayList<>();

    public void setList(List<MyInterceptorEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LogItemBinding binding = LogItemBinding.inflate(layoutInflater, parent, false);
        return new MainActivityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        holder.binding.idView.setText(String.valueOf(list.get(position).id));
        holder.binding.requestView.setText(list.get(position).request);
        holder.binding.responseView.setText(list.get(position).response);
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }

    public static class MainActivityViewHolder extends RecyclerView.ViewHolder {
        LogItemBinding binding;
        public MainActivityViewHolder(@NonNull LogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
