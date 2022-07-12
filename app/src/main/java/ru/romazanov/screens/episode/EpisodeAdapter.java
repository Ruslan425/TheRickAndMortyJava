package ru.romazanov.screens.episode;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.therickandmortyjava.databinding.EpisodeItemBinding;


public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private ArrayList<Episode> dataList = new ArrayList<>();

    public void setDataList(ArrayList<Episode> list) {
        dataList.addAll(list);
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       EpisodeItemBinding binding = EpisodeItemBinding.inflate(layoutInflater, parent, false);
       return new EpisodeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.binding.idView.setText(String.valueOf(dataList.get(position).id));
        holder.binding.nameView.setText(dataList.get(position).name);
        holder.binding.episodeView.setText(dataList.get(position).episode);
    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        EpisodeItemBinding binding;
        public EpisodeViewHolder(@NonNull  EpisodeItemBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
